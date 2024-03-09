package frc.robot.subsystems;


import com.ctre.phoenix6.hardware.TalonFX;


import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {

    public static TalonFX leftArm;
    public static TalonFX rightArm;
    //public static CANSparkMax leftArm;
    //public static CANSparkMax rightArm;
    public static DutyCycleEncoder armPosition;

    public Climber() {
       // leftArm = new CANSparkMax(Constants.Climber.leftArm, MotorType.kBrushless);
        //rightArm = new CANSparkMax(Constants.Climber.rightArm, MotorType.kBrushless);
        leftArm = new TalonFX(Constants.Climber.leftArm);
        rightArm = new TalonFX(Constants.Climber.rightArm);
        armPosition = new DutyCycleEncoder(Constants.Climber.armPosition);

       // leftArm.setIdleMode(IdleMode.kBrake);
        //rightArm.setIdleMode(IdleMode.kBrake);
    }

    public void periodic() {
        SmartDashboard.putNumber("Arm Position ", armPosition.getAbsolutePosition());
    }
}
