package frc.robot.commands;

import java.util.function.BooleanSupplier;

import com.ctre.phoenix6.controls.DutyCycleOut;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.Constants.Shooter;

public class runShooter extends Command{
    private  BooleanSupplier a;
    private BooleanSupplier b;
    public runShooter(BooleanSupplier a, BooleanSupplier b){
        this.a = a;
        this.b = b;
        
    }
    @Override
    public void execute(){
        if (a.getAsBoolean());
        frc.robot.subsystems.Shooter.leftWheel.setControl(new DutyCycleOut(Constants.Shooter.speed));

    }
}
