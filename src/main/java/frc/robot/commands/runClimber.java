package frc.robot.commands;

import com.ctre.phoenix6.controls.DutyCycleOut;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

public class runClimber extends Command {
  private DoubleSupplier a;
  private DoubleSupplier b;
  private BooleanSupplier c;
  private BooleanSupplier d;

  public runClimber(
      Climber c_Climber, DoubleSupplier a, DoubleSupplier b, BooleanSupplier c, BooleanSupplier d) {
    addRequirements(c_Climber);
    this.a = a;
    this.b = b;
    this.c = c;
    this.d = d;
  }

  @Override
  public void execute() {
    if (a.getAsDouble() != 0 && Climber.armPosition.getAbsolutePosition() < 0.79) {
      Climber.leftArm.setControl(new DutyCycleOut(a.getAsDouble()));
      Climber.rightArm.setControl(new DutyCycleOut(a.getAsDouble() * -1));
      // Climber.leftArm.setControl(new DutyCycleOut(Constants.Climber.speed));
      // Climber.rightArm.setControl(new DutyCycleOut(-Constants.Climber.speed));
    } else if (b.getAsDouble() != 0 && Climber.armPosition.getAbsolutePosition() > 0.48) {
      Climber.leftArm.setControl(new DutyCycleOut(b.getAsDouble() * -0.5));
      Climber.rightArm.setControl(new DutyCycleOut(b.getAsDouble() * 0.5));
      // Climber.leftArm.setControl(new DutyCycleOut(-Constants.Climber.speed * 0.6));
      // Climber.rightArm.setControl(new DutyCycleOut(Constants.Climber.speed * 0.6));
    } else if (c.getAsBoolean() && Climber.armPosition.getAbsolutePosition() < 0.78) {
      Climber.leftArm.setControl(new DutyCycleOut(Constants.Climber.speed));
      Climber.rightArm.setControl(new DutyCycleOut(-Constants.Climber.speed));
    } else if (d.getAsBoolean() && Climber.armPosition.getAbsolutePosition() > 0.48) {
      Climber.leftArm.setControl(new DutyCycleOut(-Constants.Climber.speed));
      Climber.rightArm.setControl(new DutyCycleOut(Constants.Climber.speed));
    } else {
      Climber.leftArm.setControl(new DutyCycleOut(0));
      Climber.rightArm.setControl(new DutyCycleOut(0));
      // Climber.leftArm.set(0);
      // Climber.rightArm.set(0);
    }
  }
}
