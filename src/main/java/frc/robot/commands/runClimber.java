package frc.robot.commands;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix6.controls.DutyCycleOut;

//import com.ctre.phoenix6.controls.DutyCycleOut;
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
        if (a.getAsDouble() != 0 && Climber.armPosition.getAbsolutePosition() < 0.90) {
            Climber.leftArm.setControl(new DutyCycleOut(Constants.Climber.speed * 3.0));
            Climber.rightArm.setControl(new DutyCycleOut(-Constants.Climber.speed * 3.0));

        } else if (b.getAsDouble() != 0 && Climber.armPosition.getAbsolutePosition() > 0.55) {
            Climber.rightArm.setControl(new DutyCycleOut(Constants.Climber.speed));
            Climber.leftArm.setControl(new DutyCycleOut(-Constants.Climber.speed));

        } else {
            Climber.rightArm.setControl(new DutyCycleOut(0));
            Climber.leftArm.setControl(new DutyCycleOut(0));
        }
    }
}