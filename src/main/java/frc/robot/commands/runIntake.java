package frc.robot.commands;

import java.util.function.BooleanSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.Command;
import  frc.robot.subsystems.Index;
import  frc.robot.subsystems.Intake;
public class runIntake extends Command{
    private BooleanSupplier a;
    private BooleanSupplier b;

    public runIntake(BooleanSupplier a, BooleanSupplier b) {
        this.a = a;
        this.b =b;


    }
    @Override
    public void execute() {
        if (a.getAsBoolean() &&  Index.storeNote.get()) {
            Intake.topRoller.set(ControlMode.PercentOutput, 1);
            Intake.bottomRoller.set(ControlMode.PercentOutput, 1);
        }
        else if (b.getAsBoolean()){
            Intake.topRoller.set(ControlMode.PercentOutput, -1);
            Intake.bottomRoller.set(ControlMode.PercentOutput, -1);
        }
        else{
            Intake.topRoller.set(ControlMode.PercentOutput, 0);
            Intake.bottomRoller.set(ControlMode.PercentOutput, 0);
        }
    }
}
