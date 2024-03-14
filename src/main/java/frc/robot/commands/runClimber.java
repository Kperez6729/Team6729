package frc.robot.commands;

import java.util.function.DoubleSupplier;
import com.ctre.phoenix6.controls.DutyCycleOut;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;

public class runClimber extends Command {
    private DoubleSupplier a;
    private DoubleSupplier b;

    public runClimber(Climber c_Climber, DoubleSupplier a, DoubleSupplier b) {
        addRequirements(c_Climber);
        this.a = a;
        this.b = b;
    }

    @Override
    public void execute() {
        if (a.getAsDouble() != 0 && Climber.armPosition.getAbsolutePosition() < 0.695) {
            Climber.leftArm.setControl(new DutyCycleOut(Constants.Climber.speed));
            Climber.rightArm.setControl(new DutyCycleOut(-Constants.Climber.speed));
            // Climber.leftArm.set(Constants.Climber.speed);
            // Climber.rightArm.set(Constants.Climber.speed);
        } else if (b.getAsDouble() != 0 && Climber.armPosition.getAbsolutePosition() > 0.44) {
            Climber.rightArm.setControl(new DutyCycleOut(Constants.Climber.speed * 0.4));
            Climber.leftArm.setControl(new DutyCycleOut(-Constants.Climber.speed * 0.4));
            // Climber.leftArm.set(-Constants.Climber.speed);
            // Climber.rightArm.set(-Constants.Climber.speed);
        } else {
            Climber.rightArm.setControl(new DutyCycleOut(0));
            Climber.leftArm.setControl(new DutyCycleOut(0));
            // Climber.leftArm.set(0);
            // Climber.rightArm.set(0);
        }
    }
}