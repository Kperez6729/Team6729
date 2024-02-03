package frc.robot;

import com.pathplanner.lib.util.PIDConstants;

import edu.wpi.first.math.geometry.Translation3d;
import edu.wpi.first.math.util.Units;
import swervelib.math.Matter;

public final class Constants {
     public static final double ROBOT_MASS = (148 - 20.3) * 0.453592; // 32lbs * kg per pound
  public static final Matter CHASSIS    = new Matter(new Translation3d(0, 0, Units.inchesToMeters(8)), ROBOT_MASS);
    public static final double LOOP_TIME  = 0.13; //s, 20ms + 110ms sprk max velocity lag
    public static final double stickDeadband = 0.1;
  public static final class AutonConstants
  {

    public static final PIDConstants TRANSLATION_PID = new PIDConstants(0.7, 0, 0);
    public static final PIDConstants ANGLE_PID   = new PIDConstants(0.4, 0, 0.01);
  }
  public static final class DrivebaseConstants
  {

    // Hold time on motor brakes when disabled
    public static final double WHEEL_LOCK_TIME = 10; // seconds
  }
  public static class OperatorConstants
  {

    // Joystick Deadband
    public static final double LEFT_X_DEADBAND  = 0.01;
    public static final double LEFT_Y_DEADBAND  = 0.01;
    public static final double RIGHT_X_DEADBAND = 0.01;
    public static final double TURN_CONSTANT    = 6;
  }
   


    /* Intake motor IDs and power value */
    public static final class Intake {
        public static final int topRoller = 21;
        public static final int bottomRoller = 20;
        public static final double speed = 0.65;
    }

    /* Indexer motor IDs, breakbeam, and power value */
    public static final class Indexer {
        public static final int leftSide = 14;
        public static final int rightSide = 15;
        public static final double speed = 0.50;
        public static final int storeNote = 1; // https://docs.wpilib.org/en/stable/docs/software/hardware-apis/sensors/digital-inputs-software.html
    }

    /* Shooter motor IDs and power value */
    public static final class Shooter {
        public static final int leftWheel = 19;
        public static final int rightWheel = 18;
        public static final double speed = 1.0;
    }

    /* Climber motor IDs, throughbore encoder, and power value */
    public static final class Climber {
        public static final int leftArm = 21;
        public static final int rightArm = 22;
        public static final double speed = 0.25;
        public static final int armPosition1 = 0;
        public static final int armPosition2 = 2;
    }
}
