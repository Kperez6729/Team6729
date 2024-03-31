// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shamper extends SubsystemBase {
  public static TalonSRX ampShooter;
  
  /** Creates a new Shamper. */
  public Shamper() {
    ampShooter = new TalonSRX(Constants.Shamper.ampShooter);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
