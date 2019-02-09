package frc.subsystems;

import frc.utils.Constants;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Solenoid;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class GamePieces extends Subsystem{
    Solenoid hatch = new Solenoid(Constants.PCM_HATCH_PISTON);
    Solenoid finger = new Solenoid(Constants.PCM_HATCH_FINGER);
    VictorSPX cargo = new VictorSPX(Constants.CAN_WHEEL_INTAKE);

    private static final double cargoMotorSpeed = 0.5;
    private static GamePieces instance;

    public static enum cargoState{GO, STOP};

    public GamePieces() {
        System.out.println("Game Piece Subsystem activated! ");
        instance = this;
    }

    public static GamePieces getInstance() {
        return instance;
    }

    //CARGO CONTROL
    public void wheelIntake(cargoState state) { 
        cargo.set(ControlMode.PercentOutput, state==cargoState.GO?cargoMotorSpeed:0);
    }
    public void wheelShoot(cargoState state) { 
        cargo.set(ControlMode.PercentOutput, state==cargoState.GO?-cargoMotorSpeed:0);
    }

    //HATCH CONTROL
    public void fingerHold() {
        finger.set(false);
    }
    public void fingerRelease() {
        finger.set(true);
    }
    public void pistonPush() {
        hatch.set(true);
    }
    public void pistonReset() {
        hatch.set(false);
    }

    @Override
    protected void initDefaultCommand() {

    }
}