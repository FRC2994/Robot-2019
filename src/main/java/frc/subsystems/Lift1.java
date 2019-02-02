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
  DoubleSolenoid LiftUpLeft = new DoubleSolenoid(Constants.PCM_LEFT_RETRACTABLE_LEG_OUT, Constants.PCM_LEFT_RETRACTABLE_LEG_IN);
  DoubleSolenoid LiftUpRight = new DoubleSolenoid(Constants.PCM_RIGHT_RETRACTABLE_LEG_OUT, Constants.PCM_RIGHT_RETRACTABLE_LEG_IN);



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

    public void initDefaultComman() {
    }

    public void LiftUpLeft() {
      System.out.println("Trying to Lilt Going UP.");
      LiftUpLeft.set(Value.kReverse);
      LiftUpRight.set(Value.kReverse);
    }    


    public void LiftUpRight() {
      System.out.println("Trying to Lilt Going DOWN.");
      LiftUpLeft.set(Value.kForward);
      LiftUpRight.set(Value.kForward);
      }

      public void LiftUpMidle() {
        System.out.println("Trying to Lilt Going to the middle.");
        LiftUpLeft.set(Value.kForward);
        LiftUpRight.set(Value.kReverse);
        }

    public void stopLiftUpLeft() {
      LiftUpLeft.set(Value.kReverse);    
    }

    public void stopLiftUpRight() {
      LiftUpRight.set(Value.kReverse);
    }
  }

