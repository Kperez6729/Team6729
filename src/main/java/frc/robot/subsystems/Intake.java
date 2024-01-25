package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase{
   public TalonSRX topRoller;
   public TalonSRX bottomRoller;
    public Intake(){
        topRoller= new TalonSRX(Constants.Intake.topRoller);
        bottomRoller = new TalonSRX(Constants.Intake.bottomRoller);
    }
    
}
