package frc.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.PWM;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ArmGamePiece extends Subsystems {
    private static CANSparkMax arm;
    private static VictorSPX hatch;
    private static VictorSPX cargo;
    private static Solenoid hatchPush;

    private static final int armMotorControllerID = 6;
    public static final double armSpeed = 20.0;

    //ARM CONTROL
    public void armForward() {
        arm = new CANSparkMax(armMotorControllerID, MotorType.kBrushless);
        arm.setInverted(false);
        arm.set(armSpeed);
    }
    public void armBackward() {
        arm = new CANSparkMax(armMotorControllerID, MotorType.kBrushless);
        arm.setInverted(true);
        arm.set(armSpeed);
    }

    //CARGO CONTROL
    public void wheelIntake() { 

    }
    public void wheelShoot() {
        
    }

    //HATCH CONTROL
    public void hatchPull() {

    }
    public void hatchPush() {

    }
}