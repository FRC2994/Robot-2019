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
    VictorSPX cargoMotor1;
    // VictorSPX cargoMotor2;
    // public DigitalInput cargoLimit;


    private static final double cargoIntakeSpeed = 0.9;
    private static final double cargoShootSpeed = -1;
    private static GamePieces instance;

    public GamePieces() {
        System.out.println("Game Piece Subsystem activated! ");
        instance = this;
        hatch = new Solenoid(Constants.PCM_HATCH_PISTON);
        finger = new DoubleSolenoid(Constants.PCM_HATCH_FINGER1,Constants.PCM_HATCH_FINGER2);
        cargoMotor1 = new VictorSPX(Constants.CAN_WHEEL_INTAKE);
        // cargoMotor2 = new VictorSPX(Constants.CAN_WHEEL_INTAKE_2);
        // cargoLimit = new DigitalInput(Constants.DIO_WHEEL_INTAKE_LIMIT);

        cargoMotor1.setNeutralMode(NeutralMode.Brake);
        // cargoMotor2.setNeutralMode(NeutralMode.Brake);
 
        cargoMotor1.configOpenloopRamp(0, 0);
        // cargoMotor2.configOpenloopRamp(0, 0);

        fingerHold();
        // cargoMotor2.follow(cargoMotor1);
        cargoMotor1.setInverted(true);
        SmartDashboard.putString("Hatch Finger", "HOLD");
    }

    public static GamePieces getInstance() {
        return instance;
    }

    //CARGO CONTROL
    public void wheelIntake() { 
        cargoMotor1.set(ControlMode.PercentOutput, cargoIntakeSpeed);
    }
    public void wheelShoot() { 
        cargoMotor1.set(ControlMode.PercentOutput, cargoShootSpeed);
    }
    public void wheelStop() {
        cargoMotor1.set(ControlMode.PercentOutput, 0);
    }
    /*
    public boolean buttonGet() {
        return cargoLimit.get();
    }*/

    //Finger
    public void fingerHold() {
        finger.set(DoubleSolenoid.Value.kForward);
        SmartDashboard.putString("Hatch Finger", "HOLD");
    }
    public void fingerRelease() {
        finger.set(DoubleSolenoid.Value.kReverse);
        SmartDashboard.putString("Hatch Finger", "RELEASED");
    }
    //Piston
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