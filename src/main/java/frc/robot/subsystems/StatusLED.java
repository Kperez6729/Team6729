// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class StatusLED extends SubsystemBase {
  public static AddressableLED leds;
  public static AddressableLEDBuffer ledsBuffer;
  public static DigitalInput detectNote;


  /** Creates a new StatusLED. */
  public StatusLED() {
    leds = new AddressableLED(Constants.StatusLED.leds);
    ledsBuffer = new AddressableLEDBuffer(60);
    leds.setLength(ledsBuffer.getLength());
    leds.start();
    detectNote = new DigitalInput(Constants.StatusLED.detectNote);
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Note in robot", detectNote.get());
  }
}
