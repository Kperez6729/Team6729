// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix6.controls.DutyCycleOut;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.limelight;

public class frontShootAuto extends Command {
  public static double bottomSpeed;
  public static double topSpeed;
  /** Creates a new Shoot. */
  public frontShootAuto(Shooter s_Shooter, Index i_Index, Intake i_Intake) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(s_Shooter, i_Index, i_Intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry ty = table.getEntry("ty");
    double targetOffsetAngle_Vertical = ty.getDouble(0.0);
    double limelightMountAngleDegrees = 12.85;
    double limelightLensHeightInches = 18;
    double GoalHeightInches = 57.0;
    double angletoGoalDegrees = limelightMountAngleDegrees + targetOffsetAngle_Vertical;
    double angletoGoalRadians= angletoGoalDegrees * (Math.PI/180);
    double distanceFromLimelighttoGoalInches = (GoalHeightInches-limelightLensHeightInches)/Math.tan(angletoGoalRadians);
    double d = distanceFromLimelighttoGoalInches;
    double exp = Math.pow(.9865 , d );
    double constant = .8567;
    if ((limelight.ID == 4.0 || limelight.ID == 7.0)){
    bottomSpeed= constant * exp ;
    topSpeed = 1.0;
   }
    else{
       bottomSpeed = .4;
       topSpeed =.4;
    }
   // bottomSpeed = 9.729219549 * Math.pow(.9875960796 , d );
    
    Shooter.topWheel.setControl(new DutyCycleOut(-topSpeed));
    Shooter.bottomWheel.setControl(new DutyCycleOut(-bottomSpeed));
    Index.leftSide.set(-Constants.Indexer.shootSpeed);
    Index.rightSide.set(Constants.Indexer.shootSpeed);
    Intake.topRoller.set(ControlMode.PercentOutput, Constants.Intake.speed);
    Intake.bottomRoller.set(ControlMode.PercentOutput, Constants.Intake.speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Shooter.topWheel.setControl(new DutyCycleOut(0));
    Shooter.bottomWheel.setControl(new DutyCycleOut(0));
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
