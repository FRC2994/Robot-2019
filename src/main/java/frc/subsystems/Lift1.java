package frc.subsystems;

// import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;

import static frc.utils.Constants.*;
// import frc.controls.EJoystick;
// import frc.controls.ButtonEntry;
// import static frc.subsystems.Subsystems.driveJoystick;
// import frc.subsystems.Subsystems;
import frc.subsystems.DriveTrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

// import frc.controls.ButtonEntry;
// import frc.controls.EJoystick;
import frc.utils.Constants;
// import frc.utils.SimPID;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;


public class Lift1 extends Subsystem {


  VictorSPX ChinUpPull = new VictorSPX(Constants.CAN_CHINUP_PULL);
	TalonSRX ChinUpRotation = new TalonSRX(Constants.CAN_CHINUP_ROTATION);
  DoubleSolenoid LiftUp = new DoubleSolenoid(Constants.PCM_RETRACTABLE_LEG_IN, Constants.PCM_RETRACTABLE_LEG_OUT);

    public void initDefaultCommand() {
    }

    public void Pull() {
      ChinUpPull.set(ControlMode.PercentOutput, -1);
    }

    public void Push() {
      ChinUpPull.set(ControlMode.PercentOutput, 1);
    }

    public void stopChinUpPull() {
      ChinUpPull.set(ControlMode.PercentOutput, 0);
    }

    public void initDefaultCom() {
    }

    public void Out() {
      ChinUpRotation.set(ControlMode.PercentOutput, 1);
    }

    public void In() {
    	ChinUpRotation.set(ControlMode.PercentOutput, -1);
    }

    public void stopChinUpRotation() {
    	ChinUpRotation.set(ControlMode.PercentOutput, 0);
    }

    public DigitalInput limitChinUpBarIn = new DigitalInput(DIO_CHINUP_LIMIT_BOTTOM);


    public void initDefaultComman() {
    }

    public void LiftUpUP() {
      System.out.println("Trying to Lilt Going UP.");
      LiftUp.set(Value.kReverse);
    }    

    public void LiftUpDOWN() {
      System.out.println("Trying to Lilt Going DOWN.");
      LiftUp.set(Value.kForward);
      }

    }