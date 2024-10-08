package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix6.controls.DutyCycleOut;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Index;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import java.util.function.BooleanSupplier;

public class runIntake extends Command {
  private BooleanSupplier a;
  private BooleanSupplier b;
  public static double slowRotate;
  public static double slowDrive;

  public runIntake(Intake i_Intake, Index i_Index, BooleanSupplier a, BooleanSupplier b) {
    addRequirements(i_Intake, i_Index);
    this.a = a;
    this.b = b;
  }

  @Override
  public void execute() {
    if (a.getAsBoolean()) {
      Intake.topRoller.set(ControlMode.PercentOutput, Constants.Intake.speed);
      Intake.bottomRoller.set(ControlMode.PercentOutput, Constants.Intake.speed);
      slowRotate = 0.25;
      slowDrive = 0.50;

    } else if (b.getAsBoolean()) {
      Index.leftSide.set(Constants.Indexer.shootSpeed);
      Index.rightSide.set(-Constants.Indexer.shootSpeed);
      Shooter.topWheel.setControl(new DutyCycleOut(.25));
      Shooter.bottomWheel.setControl(new DutyCycleOut(.25));
    } else {
      Intake.topRoller.set(ControlMode.PercentOutput, 0);
      Intake.bottomRoller.set(ControlMode.PercentOutput, 0);
      Index.leftSide.set(0);
      Index.rightSide.set(0);
      Shooter.topWheel.setControl(new DutyCycleOut(0));
      Shooter.bottomWheel.setControl(new DutyCycleOut(0));
      slowRotate = 1.0;
      slowDrive = 1.0;
    }
  }
}
