// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Swerve;
import frc.robot.subsystems.limelight;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;


public class AutoSwerve extends Command {
  private Swerve s_Swerve;
    double ts;
    public static double ty;
  public AutoSwerve(
    Swerve s_Swerve
    
    
  ) {
    this.s_Swerve = s_Swerve;
    addRequirements(s_Swerve);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    
        /* Get Values, Deadband */
   
    /* Drive */ // change 4 and 7 before a real match
         if ((limelight.ID == 4.0|| limelight.ID == 7.0 )  && (Math.abs(limelight.x) > 1   || Math.abs(limelight.by) > .5 || limelight.bs !=0)) {
               
        
               
          s_Swerve.drive(
            new Translation2d(limelight.by,0)
            .times(Constants.Swerve.maxSpeed * .025), 
            -limelight.x  *.1, 
            false, 
            true);
         } 
         else {
          s_Swerve.drive(
            new Translation2d(0,0),
            false, 
            true);}
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    s_Swerve.drive(
            new Translation2d(0,0),
            0, 
            false, 
            true);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Math.abs(limelight.x) > 1 || Math.abs(limelight.by) > .2    || s_Swerve.mSwerveMods[0].getState().speedMetersPerSecond != 0){
      return false;
    
    }
    
    else{
      return true;
    }
  }
}
