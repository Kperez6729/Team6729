package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    public static TalonSRX topRoller;
    public static TalonSRX bottomRoller;
    public static Spark leds;
    public static DigitalInput detectNote;

    public Intake() {
        topRoller = new TalonSRX(Constants.Intake.topRoller);
        bottomRoller = new TalonSRX(Constants.Intake.bottomRoller);
        leds = new Spark(Constants.Intake.leds);
        detectNote = new DigitalInput(Constants.Intake.detectNote);
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("Note in robot", detectNote.get());
    }
}
