// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class MobileMiniShooter extends SubsystemBase {
  public static TalonSRX topShooter;
  public static TalonSRX bottomShooter;

  /** Creates a new MobileMiniShooter. */
  public MobileMiniShooter() {
    topShooter = new TalonSRX(Constants.MMS.topShooter);
    bottomShooter = new TalonSRX(Constants.MMS.bottomShooter);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
