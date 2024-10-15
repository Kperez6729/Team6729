package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  public static TalonFX bottomWheel;
  public static TalonFX topWheel;

  public Shooter() {
    bottomWheel = new TalonFX(Constants.Shooter.leftWheel);
    topWheel = new TalonFX(Constants.Shooter.rightWheel);
  }
}
