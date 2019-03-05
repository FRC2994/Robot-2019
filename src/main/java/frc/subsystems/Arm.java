package frc.subsystems;


// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.utils.Constants;
import frc.commands.ZeroArm;
import edu.wpi.first.wpilibj.command.Command;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.DigitalInput;

public class Arm extends Subsystem {
  private static TalonSRX motor;
  //private static Command armZero;
  public static enum ArmMoveDirection { HI, LO };

  public int startPosition = 0;
  private int desiredPosition = 0;
  public DigitalInput LimitArmTop;
  private final int kTickIncrement = 300;
  // private final int kError = (int)(kTickIncrement*0.02);
  private final int kError = 1;

  public Arm() {
    motor = new TalonSRX(Constants.CAN_ARM);
    LimitArmTop = new DigitalInput(Constants.DIO_ARM_LIMIT_BOTTOM);
    motor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		motor.enableCurrentLimit(false);
    motor.selectProfileSlot(0, 0);
    motor.setNeutralMode(NeutralMode.Brake);
    setCurrentLimits();
    stopMotor();
    resetEncoder();
  }
  
  private void setPIDCoefficients(ArmMoveDirection dir) {
    if (dir == ArmMoveDirection.HI) {
      /* set closed loop gains in slot0, typically kF stays zero. */
      motor.config_kF(0, 0.0, 0);
      motor.config_kP(0, 0.9, 0);
      motor.config_kI(0, 0.0, 0);
      motor.config_kD(0, 0.0, 0);	
      // motor.configPeakOutputForward(1.0, 0);
    } else {
      /* set closed loop gains in slot0, typically kF stays zero. */
      motor.config_kF(0, 0.0, 0);
      motor.config_kP(0, 0.9, 0);
      motor.config_kI(0, 0.0, 0);
      motor.config_kD(0, 0.0, 0);
      motor.configPeakOutputReverse(-0.5, 0);
    }
  }
  
  public void setMotorOpenLoop(double percent) {
    if(LimitArmTop.get()){
      if(percent>0){ //TODO: change > to this < if motor is inverted
        motor.set(ControlMode.PercentOutput, percent);
      } else {
        motor.set(ControlMode.PercentOutput, 0);
      }
    } else{
      motor.set(ControlMode.PercentOutput, percent);
    }
  }
  public void move(double percent) {
    motor.set(ControlMode.PercentOutput, percent);
  }

  private void setPosition(int positionInTicks) {
    
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
  
  public int getCurrentPosition() {
    return (int)motor.getSelectedSensorPosition() - startPosition;
  }

  public void stopMotor() {
    motor.set(ControlMode.PercentOutput, 0);
  }
  
  public void keepPosition() {
    moveToPosition(getCurrentPosition());
  }

  public void resetEncoder() {
    motor.setSensorPhase(false);
    motor.setSelectedSensorPosition(0);
    motor.configClearPositionOnQuadIdx(false,1000);
    startPosition = motor.getSelectedSensorPosition();
  }

  public void zero() {
    // if (!LimitArmTop.get()) {
    //   if (!printedZeroing) {
    //     System.out.println("Arm Zeroing!! Old startPosition " + startPosition + " New startPosition " +  getCurrentPosition());
    //     printedZeroing = true;
    //   }
    //   motor.setSensorPhase(false);
    //   motor.setSelectedSensorPosition(0);
    //   motor.configClearPositionOnQuadIdx(false,1000);
    //   startPosition = getCurrentPosition();
    //   moveToPosition(0);
    // }
    // else {
    //   printedZeroing = false;
    //   moveToPosition(getCurrentPosition());
    // }
    //armZero.start();
  }
  
  public void moveDown() {
    setPIDCoefficients(ArmMoveDirection.LO);
    setPosition(getCurrentPosition()+kTickIncrement);
    // setMotorOpenLoop(-0.4);
    //motor.set(ControlMode.PercentOutput, -0.4);
    int cl_err = Math.abs(getCurrentPosition()-getDesiredPosition());
    System.out.println("MOVING DOWN... CL_ERR: " + cl_err + " kError " +kError 
    + " motorOutputVoltage " + motor.getMotorOutputVoltage() + " motorBusVoltage "  + motor.getBusVoltage()
    + " Enc: " + motor.getSelectedSensorPosition(0) + " startPosition " + startPosition
    + " getDesiredPosition " + getDesiredPosition() + " lastMotorError " + motor.getLastError() );
  }
  
  public void moveUp() {
    if (!LimitArmTop.get()) {
      setPIDCoefficients(ArmMoveDirection.HI);
      setPosition(getCurrentPosition()-kTickIncrement);
      // setMotorOpenLoop(0.4);
      //motor.set(ControlMode.PercentOutput, 0.4);
      int cl_err = Math.abs(getCurrentPosition()-getDesiredPosition());
      System.out.println("MOVING UP... CL_ERR: " + cl_err + " kError " +kError 
      + " motorOutputVoltage " + motor.getMotorOutputVoltage() + " motorBusVoltage "  + motor.getBusVoltage()
      + " Enc: " + motor.getSelectedSensorPosition(0) + " startPosition " + startPosition
      + " getDesiredPosition " + getDesiredPosition() + " lastMotorError " + motor.getLastError() );
      }
  }

  public void moveToPosition(int desiredPostion) {
    setPIDCoefficients(ArmMoveDirection.HI);
    if (LimitArmTop.get()) {
      setPosition(desiredPostion - startPosition);
    } else {
      // System.out.println("Arm Zeroing!!");
      setPosition(desiredPostion);
    }
  }
        
	public void setCurrentLimits() {
		motor.configContinuousCurrentLimit(30, 0);
		motor.configPeakCurrentLimit(40, 0);
		motor.configPeakCurrentDuration(100, 0);
		motor.enableCurrentLimit(true);
		motor.configOpenloopRamp(1, 0);
	}

  public boolean onTarget() {
      //int cl_err = Math.abs(motor.getClosedLoopError(0));
      int cl_err = Math.abs(getCurrentPosition()-getDesiredPosition());
      System.out.println("onTarget... CL_ERR: " + cl_err + " kError " +kError 
      + " motorOutputVoltage " + motor.getMotorOutputVoltage() + " motorBusVoltage "  + motor.getBusVoltage()
      + " Enc: " + motor.getSelectedSensorPosition(0) + " startPosition " + startPosition
      + " getDesiredPosition " + getDesiredPosition() + " lastMotorError " + motor.getLastError() );
        // return cl_err <= kError;
      return true;
  }

  @Override
  protected void initDefaultCommand() {
  }
}
