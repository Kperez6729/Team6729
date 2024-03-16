// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Intake;

public class frontIndex extends Command {
  /** Creates a new frontIndex. */
  public frontIndex(Index i_Index, Intake i_Intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(i_Index, i_Intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Index.leftSide.set(-Constants.Indexer.shootSpeed);
    Index.rightSide.set(Constants.Indexer.shootSpeed);
    Intake.topRoller.set(ControlMode.PercentOutput, Constants.Intake.speed);
    Intake.bottomRoller.set(ControlMode.PercentOutput, Constants.Intake.speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Index.leftSide.set(0);
    Index.rightSide.set(0);
    Intake.topRoller.set(ControlMode.PercentOutput, 0);
    Intake.bottomRoller.set(ControlMode.PercentOutput, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
