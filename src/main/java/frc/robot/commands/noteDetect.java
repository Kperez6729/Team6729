// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.StatusLED;
import java.util.function.BooleanSupplier;

public class noteDetect extends Command {
  private BooleanSupplier a;

  public noteDetect(StatusLED s_Status, BooleanSupplier a) {
    addRequirements(s_Status);
    this.a = a;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (a.getAsBoolean() == true) {
      for (var i = 0; i < StatusLED.ledsBuffer.getLength(); i++) {
        StatusLED.ledsBuffer.setRGB(i, 255, 0, 0);
      }
    } else if (a.getAsBoolean() == false) {
      for (var i = 0; i < StatusLED.ledsBuffer.getLength(); i++) {
        StatusLED.ledsBuffer.setRGB(i, 0, 255, 0);
      }
    }
    StatusLED.leds.setData(StatusLED.ledsBuffer);
    StatusLED.leds.start();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
