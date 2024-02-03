package frc.robot.commands;

import java.util.function.BooleanSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;

import  frc.robot.subsystems.Intake;
public class runIntake extends Command{
    private BooleanSupplier a;
    private BooleanSupplier b;

    public runIntake(BooleanSupplier a, BooleanSupplier b) {
        this.a = a;
        this.b = b;


    }
    @Override
    public void execute() {
        if (a.getAsBoolean() ) {
            Intake.topRoller.set(ControlMode.PercentOutput, Constants.Intake.speed);
            Intake.bottomRoller.set(ControlMode.PercentOutput, Constants.Intake.speed);
        }
        else if (b.getAsBoolean()){
            Intake.topRoller.set(ControlMode.PercentOutput, -Constants.Intake.speed);
            Intake.bottomRoller.set(ControlMode.PercentOutput, -Constants.Intake.speed);
        }
        else{
            Intake.topRoller.set(ControlMode.PercentOutput, 0);
            Intake.bottomRoller.set(ControlMode.PercentOutput, 0);
        }
    }
}
