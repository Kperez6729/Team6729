package frc.robot.commands;

import java.util.function.BooleanSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Intake;

public class runIntake extends Command {
    private BooleanSupplier a;
    private BooleanSupplier b;
    public static double slowRotate;

    public runIntake(Intake i_Intake, BooleanSupplier a, BooleanSupplier b) {
        addRequirements(i_Intake);
        this.a = a;
        this.b = b;

    }

    @Override
    public void execute() {
        if (a.getAsBoolean()) {
            Intake.topRoller.set(ControlMode.PercentOutput, Constants.Intake.speed);
            Intake.bottomRoller.set(ControlMode.PercentOutput, Constants.Intake.speed);
            slowRotate = 0.25;

        } else if (b.getAsBoolean()) {
            Intake.topRoller.set(ControlMode.PercentOutput, -Constants.Intake.speed);
            Intake.bottomRoller.set(ControlMode.PercentOutput, -Constants.Intake.speed);
        } else {
            Intake.topRoller.set(ControlMode.PercentOutput, 0);
            Intake.bottomRoller.set(ControlMode.PercentOutput, 0);
            slowRotate = 1.0;
        }
    }
}
