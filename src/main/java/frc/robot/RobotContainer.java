package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.autos.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
        /* Controllers */
        private final Joystick driver = new Joystick(0);

        /* Drive Controls */
        private final int translationAxis = XboxController.Axis.kLeftY.value;
        private final int strafeAxis = XboxController.Axis.kLeftX.value;
        private final int rotationAxis = XboxController.Axis.kRightX.value;
        private final int climberUp = XboxController.Axis.kLeftTrigger.value;
        private final int climberDown = XboxController.Axis.kRightTrigger.value;

        /* Driver Buttons */
        private final JoystickButton zeroGyro = new JoystickButton(driver, XboxController.Button.kStart.value);
        private final JoystickButton robotCentric = new JoystickButton(driver, XboxController.Button.kBack.value);
        private final JoystickButton shoot = new JoystickButton(driver, XboxController.Button.kB.value);
        // private final JoystickButton inShooter = new JoystickButton(driver,
        // XboxController.Button.kX.value);
        private final JoystickButton upIndex = new JoystickButton(driver, XboxController.Button.kY.value);
        private final JoystickButton downIndex = new JoystickButton(driver, XboxController.Button.kA.value);
        private final JoystickButton outIntake = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);
        private final JoystickButton inIntake = new JoystickButton(driver, XboxController.Button.kRightBumper.value);
        /* Subsystems */
        private final Swerve s_Swerve = new Swerve();
        private final Shooter s_Shooter = new Shooter();
        private final Intake i_Intake = new Intake();
        private final Index i_Index = new Index();
        private final Climber c_Climber = new Climber();

        /**
         * The container for the robot. Contains subsystems, OI devices, and commands.
         */
        public RobotContainer() {
                s_Swerve.setDefaultCommand(
                                new TeleopSwerve(
                                                s_Swerve,
                                                () -> -driver.getRawAxis(translationAxis),
                                                () -> -driver.getRawAxis(strafeAxis),
                                                () -> -driver.getRawAxis(rotationAxis),
                                                () -> robotCentric.getAsBoolean()));

                /*
                 * s_Shooter.setDefaultCommand(
                 * new runShooter(
                 * s_Shooter,
                 * () -> shoot.getAsBoolean(),
                 * () -> inShooter.getAsBoolean()));
                 */

                i_Index.setDefaultCommand(
                                new runIndex(
                                                i_Index,
                                                () -> upIndex.getAsBoolean(),
                                                () -> downIndex.getAsBoolean()));
                i_Intake.setDefaultCommand(
                                new runIntake(
                                                i_Intake,
                                                () -> inIntake.getAsBoolean(),
                                                () -> outIntake.getAsBoolean()));
                c_Climber.setDefaultCommand(
                                new runClimber(c_Climber,
                                                () -> driver.getRawAxis(climberUp),
                                                () -> driver.getRawAxis(climberDown)));

                // Configure the button bindings
                configureButtonBindings();
        }

        /**
         * Use this method to define your button->command mappings. Buttons can be
         * created by
         * instantiating a {@link GenericHID} or one of its subclasses ({@link
         * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
         * it to a {@link
         * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
         */
        private void configureButtonBindings() {
                /* Driver Buttons */
                zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroHeading()));

                shoot.whileTrue(new Shoot(s_Shooter, i_Index, i_Intake));
        }

        /**
         * Use this to pass the autonomous command to the main {@link Robot} class.
         *
         * @return the command to run in autonomous
         */
        public Command getAutonomousCommand() {
                // An ExampleCommand will run in autonomous
                return new exampleAuto(s_Swerve);
        }
}
