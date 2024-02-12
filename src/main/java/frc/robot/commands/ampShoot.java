// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix6.controls.DutyCycleOut;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class ampShoot extends Command {

  /** Creates a new Shoot. */
  public ampShoot(Shooter s_Shooter, Index i_Index, Intake i_Intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_Shooter, i_Index, i_Intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Shooter.leftWheel.setControl(new DutyCycleOut(Constants.Shooter.ampSpeed));
    Shooter.rightWheel.setControl(new DutyCycleOut(-Constants.Shooter.ampSpeed));
    Index.leftSide.set(Constants.Indexer.speed);
    Index.rightSide.set(-Constants.Indexer.speed);
    Intake.topRoller.set(ControlMode.PercentOutput, Constants.Intake.speed);
    Intake.bottomRoller.set(ControlMode.PercentOutput, Constants.Intake.speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Shooter.leftWheel.setControl(new DutyCycleOut(0));
    Shooter.rightWheel.setControl(new DutyCycleOut(0));
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
