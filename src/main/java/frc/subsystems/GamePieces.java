package frc.subsystems;

import frc.utils.Constants;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class GamePieces extends Subsystem{
    Solenoid hatch;
    DoubleSolenoid finger;
    VictorSPX cargo;
    public DigitalInput cargoLimit;
    private boolean fingerOut;


    private static final double cargoMotorSpeed = 0.3;
    private static GamePieces instance;

    public GamePieces() {
        System.out.println("Game Piece Subsystem activated! ");
        instance = this;
        hatch = new Solenoid(Constants.PCM_HATCH_PISTON);
        finger = new DoubleSolenoid(Constants.PCM_HATCH_FINGER1,Constants.PCM_HATCH_FINGER2);
        cargo = new VictorSPX(Constants.CAN_WHEEL_INTAKE);
        cargoLimit = new DigitalInput(Constants.DIO_WHEEL_INTAKE_LIMIT);

        cargo.setNeutralMode(NeutralMode.Brake);
        cargo.configOpenloopRamp(0, 0);

        fingerHold();
        fingerOut = false;
        SmartDashboard.putBoolean("Finger is Out", fingerOut);
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

    //Finger
    public void fingerHold() {
        finger.set(DoubleSolenoid.Value.kForward);
        fingerOut = false;
        SmartDashboard.putBoolean("Finger is Out", fingerOut);
    }
    public void fingerRelease() {
        finger.set(DoubleSolenoid.Value.kReverse);
        fingerOut = true;
        SmartDashboard.putBoolean("Finger is Out", fingerOut);
    }
    //Hatch
    public void pistonPush() {
        hatch.set(true);
        System.out.println("PUSHED PISTON");
    }
    public void pistonReset() {
        hatch.set(false);
        System.out.println("RESET PISTON");
    }

    @Override
    protected void initDefaultCommand() {

    }
}