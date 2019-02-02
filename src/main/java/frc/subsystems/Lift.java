package frc.subsystems;

// import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

import static frc.utils.Constants.*;
// import frc.subsystems.Subsystems;
import frc.subsystems.DriveTrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.utils.Constants;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;

public class Lift extends Subsystem {
//Sets up Talons Victors, Solenoid, Chin up bar, puling up mechinism and lifting mechinism
    private VictorSPX ChinUpPull;
	private TalonSRX ChinUpRotation;
	private DoubleSolenoid LiftUpLeft;
    private DoubleSolenoid LiftUpRight;
    private ChinUpStatus ChinUpBarStatus = ChinUpStatus.IN;
	private PullUpStatus intakeStatus = PullUpStatus.STOP;
	private LiftupStatus pickupStatus = LiftupStatus.UP;
    private final double ChinUpRotateNum = 0.1; // Change variable
    

    public enum ChinUpStatus {
        IN,
        OUT 
    }

	public enum LiftupStatus {
		UP,
		DOWN
	}
	
	public enum PullUpStatus {
		STOP,
		PULL,
		PUSH
    }
    
    
    public void setChinUpBarStatus(ChinUpStatus status) {
		switch (status) {
		case IN:
            ChinUpBarIn();
			break;
		case OUT:
            ChinUpBarOut();
			break;
		}
		ChinUpBarStatus = status;             
	} 


	public void setPickupStatus(LiftupStatus status) {
		switch (status) {
		case UP:
            tiltLiftUp();
			break;
		case DOWN:
            tiltLiftDown();
			break;
		}
		pickupStatus = status;
	}

	public void setIntakeStatus(PullUpStatus status) {
		switch (status) {
		case STOP:
			PullUpStop();
			break;
		case PUSH:
            PullUpPush();
			break;
		case PULL:
            PullUpPull();
			break;
        }
		intakeStatus = status;
    	
    }

	public void tiltLiftUp() {
		System.out.println("Trying to Lilt Going UP.");
		LiftUpLeft.set(Value.kReverse);
		LiftUpRight.set(Value.kReverse);
	}

	public void tiltLiftDown() {
		System.out.println("Trying to Lilt Going DOWN.");
		LiftUpLeft.set(Value.kForward);
		LiftUpRight.set(Value.kForward);
    }



    public void ChinUpBarIn() {
	  	System.out.println("Trying to bring bar in.");
        ChinUpPull(ChinUpRotateNum);
        boolean buttonToggle = false;
        //  if (button.isPressed()) {
        //     leftFrontDrive.setSelectedSensorPosition(0,0,0);
        //          }            }
    }
        
    public void ChinUpBarOut() {
        System.out.println("Trying to bring bar out.");
        ChinUpPull(ChinUpRotateNum*-1);
    }
    
    public void ChinUpPull(double value) {
	 	ChinUpPull.set(ControlMode.PercentOutput, value);
    }

    public void PullUpPull() {
		System.out.println("Trying to bring bar in.");
        ChinUpRotation(ChinUpRotateNum);
    }
        
    public void PullUpPush() {
        System.out.println("Trying to bring bar out.");
        ChinUpRotation(ChinUpRotateNum*-1);
    }

    public void PullUpStop() {
        System.out.println("Trying to bring bar out.");
        ChinUpRotation(0);
    }		
    public void ChinUpRotation(double value) {
	   	ChinUpRotation.set(ControlMode.PercentOutput, value);
    }
		
	public void ChinUpBar() {
		System.out.println("Trying to bring bar out.");
		LiftUpLeft.set(Value.kForward);
		LiftUpRight.set(Value.kForward);
    }

	@Override
	protected void initDefaultCommand() {

	}

}