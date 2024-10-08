// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix6.controls.DutyCycleOut;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.MobileMiniShooter;
import frc.robot.subsystems.Shooter;

public class ampShoot extends Command {

  /** Creates a new Shoot. */
  public ampShoot(Shooter s_Shooter, MobileMiniShooter m_MMS) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_Shooter, m_MMS);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Shooter.topWheel.setControl(new DutyCycleOut(-Constants.Shooter.ampSpeed));
    Shooter.bottomWheel.setControl(new DutyCycleOut(-Constants.Shooter.ampSpeed1));
    MobileMiniShooter.topShooter.set(ControlMode.PercentOutput, -Constants.MMS.speed);
    MobileMiniShooter.bottomShooter.set(ControlMode.PercentOutput, (-Constants.MMS.speed * 2.0));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Shooter.topWheel.setControl(new DutyCycleOut(0));
    Shooter.bottomWheel.setControl(new DutyCycleOut(0));
    MobileMiniShooter.topShooter.set(ControlMode.PercentOutput, 0);
    MobileMiniShooter.bottomShooter.set(ControlMode.PercentOutput, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
