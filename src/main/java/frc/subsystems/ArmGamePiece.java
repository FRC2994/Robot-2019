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

    private static final int armMotorControllerID = 0;
    
    public ArmGamePiece() {
        arm = new CANSparkMax(armMotorControllerID, MotorType.kBrushless);
    }

    //ARM CONTROL
    public void armForward() {

    }
    public void armBackward() {

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