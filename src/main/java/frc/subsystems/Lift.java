package frc.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.utils.Constants;

public class Lift extends Subsystem {
  
  private static final int chinUpPositionIncrement = 0;
  public static enum LiftDirection { UP, DN };
  public static enum LiftPushPullDirection { Push, DN };
  DigitalInput limitChinUp;
  VictorSPX ChinUpPull;
	TalonSRX ChinUpRotation;
  Solenoid Legs;
  int startPosition;
  int desiredPosition;
  boolean printedZeroing;
   
  public Lift() {
    limitChinUp = new DigitalInput(Constants.DIO_CHINUP_LIMIT_BOTTOM);
    ChinUpPull = new VictorSPX(Constants.CAN_CHINUP_PULL);
    ChinUpRotation = new TalonSRX(Constants.CAN_CHINUP_ROTATION);
    Legs = new Solenoid(Constants.PCM_RETRACTABLE_LEGS);
    ChinUpRotation.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
  }
  
  private void setPIDCoefficients(LiftDirection dir) {
    if (dir == LiftDirection.UP) {
      /* set closed loop gains in slot0, typically kF stays zero. */
      ChinUpRotation.config_kF(0, 0.0, 0);
      ChinUpRotation.config_kP(0, 0.2, 0);
      ChinUpRotation.config_kI(0, 0.0, 0);
      ChinUpRotation.config_kD(0, 0.0, 0);	
      ChinUpRotation.configPeakOutputForward(1.0, 0);
    } else {
      /* set closed loop gains in slot0, typically kF stays zero. */
      ChinUpRotation.config_kF(0, 0.0, 0);
      ChinUpRotation.config_kP(0, 0.1, 0);
      ChinUpRotation.config_kI(0, 0.0, 0);
      ChinUpRotation.config_kD(0, 0.0, 0);
      ChinUpRotation.configPeakOutputReverse(-0.5, 0);
    }
  }
  private void chinUpPullOpenLoop(LiftPushPullDirection direction, int speed) {
    if (direction == LiftPushPullDirection.Push) {
      ChinUpPull.set(ControlMode.Velocity, speed);
    } else {
      ChinUpPull.set(ControlMode.Velocity, -speed);
    }
  }

  private void chinUpSetPosition(int desiredPosition) {
    int position;
    if ( (desiredPosition + getDesiredPosition()) < (startPosition + Constants.ELEVATOR_POSITION_MINIMUM)) {
			position = startPosition + Constants.ELEVATOR_POSITION_MINIMUM;
		}
		else if ( (desiredPosition + getDesiredPosition()) > ( startPosition + Constants.ELEVATOR_POSITION_MAXIMUM) ) { 
			position = startPosition + Constants.ELEVATOR_POSITION_MAXIMUM;
		}
		else {
			position = desiredPosition;
		}
    ChinUpRotation.set(ControlMode.Position, position);
    this.desiredPosition = position;
  }

  public void chinUpMoveIncremental(LiftDirection dir) {
    if (limitChinUp.get()) {
      setPIDCoefficients(dir);
      if (dir == LiftDirection.UP) {
        chinUpSetPosition(getCurrentPosition() + chinUpPositionIncrement);
      } else {
        chinUpSetPosition(getCurrentPosition() - chinUpPositionIncrement);
      }
    } else {
      chinUpSetPosition(0);
    }
  }

  public void chinUpMoveToPosition(int desiredPostion) {
    if (getCurrentPosition() < desiredPostion) {
      setPIDCoefficients(LiftDirection.UP);
    } else {
      setPIDCoefficients(LiftDirection.DN);
    }
    if (limitChinUp.get()) {
      chinUpStopOpenLoop();
    }
  }
  
  public void chinUpRunOpenLoop(LiftDirection dir, double speed) {
    setPIDCoefficients(dir);
    if (!limitChinUp.get()) {
      chinUpStopOpenLoop();
    } else if (dir == LiftDirection.UP) {
      ChinUpRotation.set(ControlMode.Position, speed);
    } else {
      ChinUpRotation.set(ControlMode.Position, -speed);
    }
  }

  public void chinUpStopOpenLoop() {
    ChinUpPull.set(ControlMode.Velocity, 0);
  }

  public int getDesiredPosition() {
    return desiredPosition;
  }
  
  public int getCurrentPosition() {
    return ChinUpRotation.getSelectedSensorPosition(0) - startPosition;
  }

  /* Returns true when done */
  public boolean chinUpFindLimitSwitch() {
    if (!limitChinUp.get()) {
      ChinUpRotation.setSelectedSensorPosition(0, 0, 20);
      if (!printedZeroing) {
        System.out.println("Elevator Zeroing!! Old startPosition " + startPosition + " New startPosition " +  getCurrentPosition());
        printedZeroing = true;
      }
      startPosition = getCurrentPosition();
      chinUpSetPosition(0);
      return false;
    } else {
      printedZeroing = false;
      // chinUpSetPosition(getCurrentPosition());
      return false;
    }
  }

  public void retractableLegsUp() {
    System.out.println("Bringing RetractableLegs UP.");
    Legs.set(false);
  }    

  public void retractableLegsDown() {
    System.out.println("Bringing RetractableLegs DOWN.");
    Legs.set(true);  
  }

  public boolean onTarget() {
    int cl_err = ChinUpRotation.getClosedLoopError(0);
    System.out.println("CL_ERR: " + Math.abs(cl_err) + " motorOut: " + ChinUpRotation.getMotorOutputPercent()
    + " Enc: " + ChinUpRotation.getSelectedSensorPosition(0) + " startPosition " + startPosition
    + " getDesiredPosition " + getDesiredPosition());
    return ChinUpRotation.getClosedLoopError(0) < 2000;
  }
  
  @Override
  public void initDefaultCommand() {
  }

}