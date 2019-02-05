package frc.subsystems;

import frc.utils.Constants;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class GamePieces extends Subsystems {
    // DoubleSolenoid hatch = new DoubleSolenoid(Constants.PCM_PISTON_PUSH, Constants.PCM_PISTON_PUSH);
    DoubleSolenoid finger = new DoubleSolenoid(Constants.PCM_HATCH_FINGER_HOLD, Constants.PCM_HATCH_FINGER_RETRACT);
    VictorSPX cargo = new VictorSPX(Constants.CAN_WHEEL_INTAKE);

    private static final double cargoMotorSpeed = 0.5;

    public GamePieces() {
        System.out.println("Game Piece Subsystem activated! ");
    }

    public void wheelIntake() { 
        cargo.set(ControlMode.PercentOutput, cargoMotorSpeed);
    }
    public void wheelShoot() { 
        cargo.set(ControlMode.PercentOutput, -cargoMotorSpeed);
    }

    //HATCH CONTROL
    public void hatchPull() {
        finger.set(Value.kForward);
    }
    public void hatchPush() {
        finger.set(Value.kReverse);
        // hatch.set(Value.kForward);
        // hatch.set(Value.kReverse);
    }
}