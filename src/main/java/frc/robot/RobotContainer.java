package frc.robot;

import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final SendableChooser<Command> autoChooser;
  /* Controllers */
  private final Joystick driver = new Joystick(0);
  private final Joystick operator = new Joystick(1);

  /* Drive Controls */
  private final int translationAxis = PS4Controller.Axis.kLeftY.value;
  private final int strafeAxis = PS4Controller.Axis.kLeftX.value;
  private final int rotationAxis = PS4Controller.Axis.kRightX.value;

  /* Driver Buttons */
  private final int climberDown = XboxController.Axis.kLeftTrigger.value;
  private final int climberUp = XboxController.Axis.kRightTrigger.value;
  private final JoystickButton zeroGyro =
      new JoystickButton(driver, PS4Controller.Button.kOptions.value);
  private final JoystickButton robotCentric =
      new JoystickButton(driver, XboxController.Button.kRightStick.value);
  private final JoystickButton shoot = new JoystickButton(driver, XboxController.Button.kY.value);
  private final JoystickButton ampShoot =
      new JoystickButton(driver, XboxController.Button.kB.value);
  private final JoystickButton sideShooter =
      new JoystickButton(driver, XboxController.Button.kX.value);
  private final JoystickButton outIntake =
      new JoystickButton(driver, XboxController.Button.kLeftBumper.value);
  private final JoystickButton inIntake =
      new JoystickButton(driver, XboxController.Button.kRightBumper.value);

  /* Operator Buttons */
  private final JoystickButton opShoot =
      new JoystickButton(operator, PS4Controller.Button.kTriangle.value);
  private final JoystickButton opAmpShoot =
      new JoystickButton(operator, PS4Controller.Button.kSquare.value);
  private final JoystickButton opSideShoot =
      new JoystickButton(operator, PS4Controller.Button.kCircle.value);
  private final JoystickButton opClimbUp =
      new JoystickButton(operator, PS4Controller.Button.kR1.value);
  private final JoystickButton opClimbDown =
      new JoystickButton(operator, PS4Controller.Button.kL1.value);

  /* Subsystems */
  private final Swerve s_Swerve = new Swerve();
  private final Shooter s_Shooter = new Shooter();
  private final Intake i_Intake = new Intake();
  private final Index i_Index = new Index();
  private final Climber c_Climber = new Climber();
  private final StatusLED s_Status = new StatusLED();
  private final MobileMiniShooter m_MMS = new MobileMiniShooter();
  private final limelight l_Limelight = new limelight();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    s_Swerve.setDefaultCommand(
        new TeleopSwerve(
            s_Swerve,
            () -> -driver.getRawAxis(translationAxis),
            () -> -driver.getRawAxis(strafeAxis) ,
            () -> -driver.getRawAxis(rotationAxis) ,
            () -> robotCentric.getAsBoolean()));
    
    i_Intake.setDefaultCommand(
        new runIntake(
            i_Intake, i_Index, () -> inIntake.getAsBoolean(), () -> outIntake.getAsBoolean()));

    c_Climber.setDefaultCommand(
        new runClimber(
            c_Climber,
            () -> driver.getRawAxis(climberUp),
            () -> driver.getRawAxis(climberDown),
            () -> opClimbUp.getAsBoolean(),
            () -> opClimbDown.getAsBoolean()));

    s_Status.setDefaultCommand(new noteDetect(s_Status, () -> StatusLED.detectNote.get()));

    NamedCommands.registerCommand("side shoot", new sideShootAuto(s_Shooter, i_Index, i_Intake));
    NamedCommands.registerCommand("front ", new frontShootAuto(s_Shooter, i_Index, i_Intake));
    NamedCommands.registerCommand("Intake", new upIntake(i_Intake));
    NamedCommands.registerCommand("stop Intake", new stopIntake(i_Intake));
    NamedCommands.registerCommand("Stop shoot", new stopShoot(s_Shooter, i_Index, i_Intake));
    NamedCommands.registerCommand("Auto Align", new AutoSwerve(s_Swerve));
    NamedCommands.registerCommand("front Shoot", new frontShoot(s_Shooter)
            .alongWith(
                new SequentialCommandGroup(
                    Commands.waitSeconds(0.5), new frontIndex(i_Index, i_Intake))));;
    NamedCommands.registerCommand("frontIndex", new frontIndex(i_Index, i_Intake));
    // Configure the button bindings
    configureButtonBindings();
    autoChooser = AutoBuilder.buildAutoChooser();
    SmartDashboard.putData("Auto Chooser", autoChooser);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    /* Driver Buttons */
    zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroHeading()));

    shoot.whileTrue(
        new frontShoot(s_Shooter)
            .alongWith(
                new SequentialCommandGroup(
                    Commands.waitSeconds(0.5), new frontIndex(i_Index, i_Intake))));
    sideShooter.whileTrue(
        new sideShoot(s_Shooter)
            .alongWith(
                new SequentialCommandGroup(
                    Commands.waitSeconds(0.5), new sideIndex(i_Index, i_Intake))));
    ampShoot.whileTrue(new ampShoot(s_Shooter, m_MMS).alongWith(new ampIndex(i_Index, i_Intake)));
    /* Operator Buttons */
    opShoot.whileTrue(
        new frontShoot(s_Shooter)
            .alongWith(
                new SequentialCommandGroup(
                    Commands.waitSeconds(0.5), new frontIndex(i_Index, i_Intake))));
    opSideShoot.whileTrue(
        new sideShoot(s_Shooter)
            .alongWith(
                new SequentialCommandGroup(
                    Commands.waitSeconds(0.5), new sideIndex(i_Index, i_Intake))));
    opAmpShoot.whileTrue(new ampShoot(s_Shooter, m_MMS).alongWith(new ampIndex(i_Index, i_Intake)));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return autoChooser.getSelected();
  }
}
