package frc.subsystems;

import frc.utils.Constants;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DigitalInput;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class GamePieces extends Subsystem{
    Solenoid hatch = new Solenoid(Constants.PCM_HATCH_PISTON);
    DoubleSolenoid finger = new DoubleSolenoid(Constants.PCM_HATCH_FINGER1,Constants.PCM_HATCH_FINGER2);
    VictorSPX cargo = new VictorSPX(Constants.CAN_WHEEL_INTAKE);
    DigitalInput cargoLimit = new DigitalInput(Constants.DIO_WHEEL_INTAKE_LIMIT);


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
        cargo.set(ControlMode.PercentOutput, -1);
    }
    public void wheelStop() {
        cargo.set(ControlMode.PercentOutput, 0);
    }
    public boolean buttonGet() {
        return cargoLimit.get();
    }

    // //HATCH CONTROL
    public void fingerHold() {
        finger.set(DoubleSolenoid.Value.kForward);
    }
    public void fingerRelease() {
        finger.set(DoubleSolenoid.Value.kReverse);
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