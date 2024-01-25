package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.DutyCycleEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase{
    
    public TalonFX leftArm;
    public TalonFX rightArm;
    public DutyCycleEncoder armPosition;
    public Climber() {
        leftArm = new TalonFX(Constants.Climber.leftArm);
        rightArm = new TalonFX(Constants.Climber.rightArm);
        armPosition = new DutyCycleEncoder(Constants.Climber.armPosition);
    }
}
