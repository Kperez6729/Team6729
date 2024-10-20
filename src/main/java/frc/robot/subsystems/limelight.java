package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.AutoSwerve;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;



import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class limelight extends  SubsystemBase {
   
 public static double x;
 public static double y;
 public double area;
 public static double s;
 public static double d;
 public static double ID;
 public static double by;
 public static double bs;

    public limelight() {
     

    }
    public void  periodic(){
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    NetworkTableEntry ts = table.getEntry("ts");
    NetworkTableEntry tid = table.getEntry("tid");

//read values periodically
    x = tx.getDouble(0.0);
    y = ty.getDouble(0.0);
    area = ta.getDouble(0.0);
    s = ts.getDouble(0.0);
    ID = tid.getDouble(0.0);
     double targetOffsetAngle_Vertical = ty.getDouble(0.0);
     double limelightMountAngleDegrees = 12.85;
     double limelightLensHeightInches = 18;
     double GoalHeightInches = 57.0;
     double angletoGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
     double angletoGoalRadians= angletoGoalDegrees * (3.14159/180);
     double distanceFromLimelighttoGoalInches = (GoalHeightInches-limelightLensHeightInches)/Math.tan(angletoGoalRadians);
     double d = distanceFromLimelighttoGoalInches;
      by = y - 10.3;
      if (s <= 85.75 && s >= 85.25) bs = 0;
      else if (s>=85.75 || s<= 50) bs =-5;
      else if (s< 85.25 && s> 50) bs =5;  

   // double x = -.0038633677 * d;
   // double constant = .6809971663;
    //bottomSpeed = x + constant;
//set shooter speeds based on distance
     
    

    //post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", tx.getDouble(0.0));
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);
    SmartDashboard.putNumber("LimelightS", s);
    SmartDashboard.putNumber("distance Frome Apriltag", distanceFromLimelighttoGoalInches);
    SmartDashboard.putNumber("td", d-90);
    SmartDashboard.putNumber("ty", by);
    SmartDashboard.putNumber("bs", bs);
    
    }
}