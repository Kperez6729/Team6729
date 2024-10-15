package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.limelight;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class TeleopSwerve extends Command {
  private Swerve s_Swerve;
  private DoubleSupplier translationSup;
  private DoubleSupplier strafeSup;
  private DoubleSupplier rotationSup;
  private BooleanSupplier robotCentricSup;
  

  public TeleopSwerve(
      Swerve s_Swerve,
      DoubleSupplier translationSup,
      DoubleSupplier strafeSup,
      DoubleSupplier rotationSup,
      BooleanSupplier robotCentricSup) {
    this.s_Swerve = s_Swerve;
    addRequirements(s_Swerve);

    this.translationSup = translationSup;
    this.strafeSup = strafeSup;
    this.rotationSup = rotationSup;
    this.robotCentricSup = robotCentricSup;
    
  }

  @Override
  public void execute() {
    /* Get Values, Deadband */
    double translationVal =
        MathUtil.applyDeadband(translationSup.getAsDouble(), Constants.stickDeadband);
    double strafeVal = MathUtil.applyDeadband(strafeSup.getAsDouble(), Constants.stickDeadband);
    double rotationVal = MathUtil.applyDeadband(rotationSup.getAsDouble(), Constants.stickDeadband);
    
    /* Drive */
         if ((limelight.ID == 4.0 || limelight.ID == 7.0) && Math.abs(translationVal) < .1 && Math.abs(strafeVal) < .1 && Math.abs(rotationVal) < .1 && Math.abs(limelight.x) > 2) {
          s_Swerve.drive(
            new Translation2d(0,0), limelight.x *-.02, true, false);
         }                      
         else{
      s_Swerve.drive(
        new Translation2d(translationVal, strafeVal)
            .times(Constants.Swerve.maxSpeed * runIntake.slowDrive),
        rotationVal * Constants.Swerve.maxAngularVelocity * runIntake.slowRotate,
        !robotCentricSup.getAsBoolean(),
        false);
    }}
}
