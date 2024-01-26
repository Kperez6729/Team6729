package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    public static TalonFX leftWheel;
    public static TalonFX rightWheel;
    public Shooter() {
        leftWheel = new TalonFX(Constants.Shooter.leftWheel);
        rightWheel = new TalonFX(Constants.Shooter.rightWheel);
    }
}
