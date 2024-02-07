package frc.robot.commands;

import java.util.function.BooleanSupplier;

import com.ctre.phoenix6.controls.DutyCycleOut;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Climber;

public class runClimber extends Command{
    private BooleanSupplier a;
    private BooleanSupplier b;
    public runClimber(Climber c_Climber, BooleanSupplier a, BooleanSupplier b){
        addRequirements(c_Climber);
        this.a = a;
        this.b = b;
    }
    @Override
    public void execute(){
        if (a.getAsBoolean()) {
            Climber.leftArm.setControl(new DutyCycleOut(-Constants.Climber.speed));
            Climber.rightArm.setControl(new DutyCycleOut(Constants.Climber.speed));
        }
        else if(b.getAsBoolean()){
            Climber.rightArm.setControl(new DutyCycleOut(-Constants.Climber.speed));
            Climber.leftArm.setControl(new DutyCycleOut(Constants.Climber.speed));
        }
        else{
            Climber.rightArm.setControl(new DutyCycleOut (0));
            Climber.leftArm.setControl(new DutyCycleOut(0));
        }
    }
}