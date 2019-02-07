package frc.subsystems;

import frc.utils.Constants;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class GamePieces extends Subsystems {
    DoubleSolenoid hatch = new DoubleSolenoid(Constants.PCM_PISTON_PUSH, Constants.PCM_PISTON_RETRACT);
    DoubleSolenoid finger = new DoubleSolenoid(Constants.PCM_HATCH_FINGER_HOLD, Constants.PCM_HATCH_FINGER_RETRACT);
    VictorSPX cargo = new VictorSPX(Constants.CAN_WHEEL_INTAKE);

    private static final double cargoMotorSpeed = 0.5;
    private static GamePieces instance;

    public GamePieces() {
        System.out.println("Game Piece Subsystem activated! ");
        instance = this;
    }

    public static GamePieces getInstance() {
        return instance;
    }

    //CARGO CONTROL
    public void wheelIntake() { 
        cargo.set(ControlMode.PercentOutput, cargoMotorSpeed);
    }
    public void wheelShoot() { 
        cargo.set(ControlMode.PercentOutput, -cargoMotorSpeed);
    }
    public void wheelStop() {
        cargo.set(ControlMode.PercentOutput, 0);
    }

    //HATCH CONTROL
    public void fingerHold() {
        finger.set(Value.kForward);
    }
    public void fingerRelease() {
        finger.set(Value.kReverse);
    }
    public void pistonPush() {
        hatch.set(Value.kForward);
    }
    public void pistonReset() {
        hatch.set(Value.kReverse);
    }
}