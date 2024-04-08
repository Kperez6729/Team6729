// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix6.controls.DutyCycleOut;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;

public class frontShoot extends Command {

  /** Creates a new Shoot. */
  public frontShoot(Shooter s_Shooter) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_Shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Shooter.leftWheel.setControl(new DutyCycleOut(-Constants.Shooter.speed));
    Shooter.rightWheel.setControl(new DutyCycleOut(Constants.Shooter.speed));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Shooter.leftWheel.setControl(new DutyCycleOut(0));
    Shooter.rightWheel.setControl(new DutyCycleOut(0));
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
