package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Index extends SubsystemBase{
    public CANSparkMax leftSide;
    public CANSparkMax rightSide;
    public DigitalInput storeNote;
    public Index() {
        leftSide = new CANSparkMax(Constants.Indexer.leftSide, CANSparkLowLevel.MotorType.kBrushless);
        rightSide = new CANSparkMax(Constants.Indexer.rightSide, CANSparkLowLevel.MotorType.kBrushless);
        storeNote = new DigitalInput(Constants.Indexer.storeNote);
    }
}
