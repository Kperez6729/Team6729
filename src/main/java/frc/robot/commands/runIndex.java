package frc.robot.commands;

import java.util.function.BooleanSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Index;

public class runIndex extends Command{
    private BooleanSupplier a;
    private BooleanSupplier b;
    public runIndex(BooleanSupplier a, BooleanSupplier b){
        this.a = a;
        this.b = b;
    }
    @Override
    public void execute(){
        if (a.getAsBoolean()) {
            Index.leftSide.set(Constants.Indexer.speed);
            Index.rightSide.set(-Constants.Indexer.speed);
        }
        else if(b.getAsBoolean()){
            Index.rightSide.set(Constants.Indexer.speed);
            Index.leftSide.set(-Constants.Indexer.speed);
        }
        else{
            Index.rightSide.set(0);
            Index.leftSide.set(0);
        }
    }
}
