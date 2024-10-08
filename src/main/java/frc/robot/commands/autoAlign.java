// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Swerve;
import frc.robot.Constants;
public class autoAlign extends Command {
  private Swerve s_Swerve;
  private NetworkTable table;
  private double x;
  private double y;
  private double a;
  private double s;
  private double d;
  /** Creates a new autoAlign. **/
  public autoAlign(Swerve s_Swerve) {
    addRequirements(s_Swerve);
    
  
  
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    NetworkTableEntry ts = table.getEntry("ts");
    x = tx.getDouble(0.0);
    y = ty.getDouble(0.0);
    a = ta.getDouble(0.0);
    s = ts.getDouble(0.0);
    if (Math.abs(y) <= 2) y = 0;
    else if ( y > 2 && y <= 3) y = .01;
    else if (y < -2 && y >=-3) y = -0.01;
     
    if (Math.abs(x) <= 5) x = 0;
    else if ( x > 5 && x <= 7) x = .1;
    else if ( x < -5 && x >= -7) x = -.1;
    else if( x>=15) x = 14;
    else if (x <= -15) x = -14;

    if (Math.abs(a) >= 2) a = 0;
    if (s <= 2 || s >= 88) s = 0;
    else if (s>=2 && s<= 50) s =-5;
    else if (s< 88 && s> 50) s =5;    
     
    s_Swerve.drive(    
    new Translation2d(y, -s)
        .times(Constants.Swerve.maxSpeed * .01),
        -x * Constants.Swerve.maxAngularVelocity *.01,
        false,
        true);
  }
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    s_Swerve.drive(    
    new Translation2d(0, 0)
        .times(Constants.Swerve.maxSpeed * .01),
        0 * Constants.Swerve.maxAngularVelocity *.01,
        false,
        true);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Math.abs(x) < 5 && Math.abs(y) <= 2 && (s <= 2 || s >= 88))
      return true;
      else return false;
  }
}
