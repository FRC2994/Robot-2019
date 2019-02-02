package frc.subsystems;

import frc.utils.Constants;
import frc.utils.Constants.*;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.PWM;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class GamePieces extends Subsystems {
    DoubleSolenoid hatch = new DoubleSolenoid(Constants.PCM_PISTON_PUSH, Constants.PCM_PISTON_PUSH);
    DoubleSolenoid finger = new DoubleSolenoid(Constants.PCM_FINGER_HOLD, Constants.PCM_PISTON_RETRACT);
    VictorSPX cargo = new VictorSPX(Constants.CAN_WHEEL_INTAKE);

    public GamePieces() {
        System.out.println("Game Piece Subsystem activated! ");
    }

    public void wheelIntake() { 
        cargo.set(ControlMode.PercentOutput, 1);
    }
    public void wheelShoot() { 
        cargo.set(ControlMode.PercentOutput, -1);
    }

    //HATCH CONTROL
    public void hatchPull() {
        finger.set(Value.kForward);
    }
    public void hatchPush() {
        finger.set(Value.kReverse);
        hatch.set(Value.kForward);
        hatch.set(Value.kReverse);
    }
}