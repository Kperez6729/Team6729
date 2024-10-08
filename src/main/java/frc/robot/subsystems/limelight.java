package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class limelight extends  SubsystemBase {
   
 public double x;
 public double y;
 public double area;
 public double s;
 

    public limelight() {
     

    }
    public void  periodic(){
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    NetworkTableEntry ts = table.getEntry("ts");

//read values periodically
    x = tx.getDouble(0.0);
    y = ty.getDouble(0.0);
    area = ta.getDouble(0.0);
    s = ts.getDouble(0.0);
     double targetOffsetAngle_Vertical = ty.getDouble(0.0);
     double limelightMountAngleDegrees = 22.1;
     double limelightLensHeightInches = 18.0;
     double GoalHeightInches = 66.0;
     double angletoGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
     double angletoGoalRadians= angletoGoalDegrees * (3.14159/180);
     double distanceFromLimelighttoGoalInches = (GoalHeightInches-limelightLensHeightInches)/Math.tan(angletoGoalRadians);
     
//set shooter speeds based on distance
        

    //post to smart dashboard periodically
    SmartDashboard.putNumber("LimelightX", tx.getDouble(0.0));
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightArea", area);
    SmartDashboard.putNumber("LimelightS", s);
    SmartDashboard.putNumber("distance Frome Apriltag", distanceFromLimelighttoGoalInches);
    }
}