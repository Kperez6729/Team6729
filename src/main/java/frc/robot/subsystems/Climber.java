package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {

    // public static TalonFX leftArm;
    // public static TalonFX rightArm;
    public static TalonFX leftArm;
    public static TalonFX rightArm;
    public static DutyCycleEncoder armPosition;

    public Climber() {
        leftArm = new TalonFX(Constants.Climber.leftArm);
        rightArm = new TalonFX(Constants.Climber.rightArm);
        armPosition = new DutyCycleEncoder(Constants.Climber.armPosition);
        // armPosition.setPositionOffset(0.89);
        leftArm.setNeutralMode(NeutralModeValue.Brake);
        rightArm.setNeutralMode(NeutralModeValue.Brake);
    }

    public void periodic() {
        SmartDashboard.putNumber("Arm Position ", armPosition.getAbsolutePosition());
    }
}
