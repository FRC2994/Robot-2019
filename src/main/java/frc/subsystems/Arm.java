package frc.subsystems;


// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.utils.Constants;
import frc.commands.ZeroArm;
import edu.wpi.first.wpilibj.command.Command;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.DigitalInput;

public class Arm extends Subsystem {
  private static TalonSRX motor;
  //private static Command armZero;
  public static enum ArmMoveDirection { HI, LO };

  public int startPosition = 0;
  private int desiredPosition = 0;
  public DigitalInput LimitArmTop;
  private boolean printedZeroing;
  private final double kError = 1000;

  public Arm() {
    motor = new TalonSRX(Constants.CAN_ARM);
    LimitArmTop = new DigitalInput(Constants.DIO_ARM_LIMIT_BOTTOM);
    motor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
    motor.selectProfileSlot(0, 0);
    setCurrentLimits();
    stopMotor();
    resetEncoder();
  }
  
  private void setPIDCoefficients(ArmMoveDirection dir) {
    if (dir == ArmMoveDirection.HI) {
      /* set closed loop gains in slot0, typically kF stays zero. */
      motor.config_kF(0, 0.0, 0);
      motor.config_kP(0, 0.5, 0);
      motor.config_kI(0, 0.0, 0);
      motor.config_kD(0, 0.0, 0);	
      // motor.configPeakOutputForward(1.0, 0);
    } else {
      /* set closed loop gains in slot0, typically kF stays zero. */
      motor.config_kF(0, 0.0, 0);
      motor.config_kP(0, 0.3, 0);
      motor.config_kI(0, 0.0, 0);
      motor.config_kD(0, 0.0, 0);
      motor.configPeakOutputReverse(-0.5, 0);
    }
  }
  
  public void setMotorOpenLoop(double percent) {
    if(LimitArmTop.get()){
      if(percent>0){ //TODO: change > to this < if motor is inverted
        motor.set(ControlMode.PercentOutput, percent);
      }
      else {
      motor.set(ControlMode.PercentOutput, 0);
      }
    }
    else{
      motor.set(ControlMode.PercentOutput, percent);
    }
  }

  public void setPosition(int positionInTicks) {
    
    // if (positionInTicks < 50 && positionInTicks < this.positionInTicks) {
    //			positionInTicks = 50;
    //		}
    //		System.out.println("Trying to move Arm in Closed Loop... currentPosition " + getDesiredPosition() 
    //			+ " encPos "  + getDesiredPosition() + " positionInTicks " + positionInTicks + ".");
    //		System.out.println("New desired: " + (positionInTicks + startPosition));
    desiredPosition = positionInTicks + startPosition;
    motor.set(ControlMode.Position, desiredPosition);
  }

  public int getDesiredPosition() {
    return desiredPosition;
  }
  
  public int getRealPosition() {
    return (int)motor.getSelectedSensorPosition() - startPosition;
  }

  public void stopMotor() {
      setMotorOpenLoop(0);
  }
  
  public void resetEncoder() {
        motor.setSensorPhase(false);
    motor.setSelectedSensorPosition(0);
    motor.configClearPositionOnQuadIdx(false,1000);
  }
  public void zero() {
    // if (!LimitArmTop.get()) {
    //   if (!printedZeroing) {
    //     System.out.println("Arm Zeroing!! Old startPosition " + startPosition + " New startPosition " +  getRealPosition());
    //     printedZeroing = true;
    //   }
    //   motor.setSensorPhase(false);
    //   motor.setSelectedSensorPosition(0);
    //   motor.configClearPositionOnQuadIdx(false,1000);
    //   startPosition = getRealPosition();
    //   setPosition(0);
    // }
    // else {
    //   printedZeroing = false;
    //   setPosition(getRealPosition());
    // }
    //armZero.start();
  }
  
  public void moveDown() {
    setPIDCoefficients(ArmMoveDirection.LO);
    setMotorOpenLoop(-0.1);
  }
  
  private void moveUp() {
    if (LimitArmTop.get()) {
      setPIDCoefficients(ArmMoveDirection.HI);
      setMotorOpenLoop(0.1);
    } else {
      zero();
    }
  }

  public void moveToPosition(int desiredPostion) {
    if (LimitArmTop.get()) {
      setPIDCoefficients(ArmMoveDirection.HI);
      setPosition(desiredPostion - startPosition);
    } else {
      // System.out.println("Arm Zeroing!!");
      setPosition(desiredPostion);
    }
  }
        
	public void setCurrentLimits() {
		motor.configContinuousCurrentLimit(15, 0);
		motor.configContinuousCurrentLimit(15, 0);

		motor.configPeakCurrentLimit(20, 0);
		motor.configPeakCurrentLimit(20, 0);
		
		motor.configPeakCurrentDuration(100, 0);
		motor.configPeakCurrentDuration(100, 0);

		motor.enableCurrentLimit(true);
		motor.enableCurrentLimit(true);
				
		motor.configOpenloopRamp(1, 0);
		motor.configOpenloopRamp(1, 0);
	}

  public boolean onTarget() {
      int cl_err = motor.getClosedLoopError(0);
      System.out.println("CL_ERR: " + Math.abs(cl_err) + " motorOut: " + motor.getMotorOutputPercent()
      + " Enc: " + motor.getSelectedSensorPosition(0) + " startPosition " + startPosition
      + " getDesiredPosition " + getDesiredPosition());
      return motor.getClosedLoopError(0) < kError;
  }

  public int motorEncoder() {
    return motor.getSelectedSensorPosition(0);
  }
  @Override
  protected void initDefaultCommand() {

  }
}
