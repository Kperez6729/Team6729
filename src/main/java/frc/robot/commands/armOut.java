// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;

public class armOut extends Command {
  /** Creates a new armOut. */
  public armOut(Climber c_Climber) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(c_Climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (Climber.armPosition.getAbsolutePosition() > 0.812) {
      Climber.leftArm.set(Constants.Climber.speed);
      Climber.rightArm.set(Constants.Climber.speed);
    } else {
      Climber.leftArm.set(0);
      Climber.rightArm.set(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Climber.leftArm.set(0);
    Climber.rightArm.set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
