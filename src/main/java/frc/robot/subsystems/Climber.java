package frc.robot.subsystems;

//import com.ctre.phoenix6.hardware.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {

    // public static TalonFX leftArm;
    // public static TalonFX rightArm;
    public static CANSparkMax leftArm;
    public static CANSparkMax rightArm;
    public static DutyCycleEncoder armPosition;

    public Climber() {
        leftArm = new CANSparkMax(Constants.Climber.leftArm, MotorType.kBrushless);
        rightArm = new CANSparkMax(Constants.Climber.rightArm, MotorType.kBrushless);
        armPosition = new DutyCycleEncoder(Constants.Climber.armPosition);
    }

    public void periodic() {
        SmartDashboard.putNumber("Arm Position ", armPosition.getAbsolutePosition());
    }
}
