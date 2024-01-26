package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase{
    
    public static TalonFX leftArm;
    public static TalonFX rightArm;
    public static Encoder armPosition;
    public Climber() {
        leftArm = new TalonFX(Constants.Climber.leftArm);
        rightArm = new TalonFX(Constants.Climber.rightArm);
        armPosition = new Encoder(Constants.Climber.armPosition1, Constants.Climber.armPosition2);
    }
}
