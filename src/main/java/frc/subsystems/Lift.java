package frc.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.commands.ZeroLift;
import edu.wpi.first.wpilibj.command.Command;

import frc.utils.Constants;

public class Lift extends Subsystem {
  
  private static final int chinUpPositionIncrement = 0;
  public static enum LiftDirection { UP, DN };
  public static enum LiftPushPullDirection { Push, DN };
  public DigitalInput limitChinUp;
  TalonSRX ChinUpArm;
  VictorSPX LiftChinUpIntake;
  DoubleSolenoid Legs;
  //Command liftZero;
  public int startPosition;
  int desiredPosition;
  boolean printedZeroing;
  
  public Lift() {
    limitChinUp = new DigitalInput(Constants.DIO_CHINUP_LIMIT_BOTTOM);
    ChinUpArm = new TalonSRX(Constants.CAN_CHINUP_ARM);
    LiftChinUpIntake = new VictorSPX(Constants.CAN_CHINUP_WHEEL_INTAKE);
    Legs = new DoubleSolenoid(Constants.PCM_RETRACTABLE_LEGS1,Constants.PCM_RETRACTABLE_LEGS2);
    //liftZero = new ZeroLift();
    ChinUpArm.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);    
  
    System.out.println("Lift Subsystem activated! ");
  }

   //CHIN UP BAR

   private void armSetPIDCoefficients(LiftDirection dir) {
    if (dir == LiftDirection.UP) {
      /* set closed loop gains in slot0, typically kF stays zero. */
      ChinUpArm.config_kF(0, 0.0, 0);
      ChinUpArm.config_kP(0, 0.2, 0);
      ChinUpArm.config_kI(0, 0.0, 0);
      ChinUpArm.config_kD(0, 0.0, 0);	
      ChinUpArm.configPeakOutputForward(1.0, 0);
    } else {
      /* set closed loop gains in slot0, typically kF stays zero. */
      ChinUpArm.config_kF(0, 0.0, 0);
      ChinUpArm.config_kP(0, 0.1, 0);
      ChinUpArm.config_kI(0, 0.0, 0);
      ChinUpArm.config_kD(0, 0.0, 0);
      ChinUpArm.configPeakOutputReverse(-0.5, 0);
    }
  }

  private void chinUpSetPIDCoefficients(LiftDirection dir) {
    if (dir == LiftDirection.UP) {
      /* set closed loop gains in slot0, typically kF stays zero. */
      LiftChinUpIntake.config_kF(0, 0.0, 0);
      LiftChinUpIntake.config_kP(0, 0.2, 0);
      LiftChinUpIntake.config_kI(0, 0.0, 0);
      LiftChinUpIntake.config_kD(0, 0.0, 0);	
      LiftChinUpIntake.configPeakOutputForward(1.0, 0);
    } else {
      /* set closed loop gains in slot0, typically kF stays zero. */
      LiftChinUpIntake.config_kF(0, 0.0, 0);
      LiftChinUpIntake.config_kP(0, 0.1, 0);
      LiftChinUpIntake.config_kI(0, 0.0, 0);
      LiftChinUpIntake.config_kD(0, 0.0, 0);
      LiftChinUpIntake.configPeakOutputReverse(-0.5, 0);
    }
  }

  private void chinUpArmSetPosition(int desiredPosition) {
    int position;
    if ( (desiredPosition +armGetDesiredPosition()) < (startPosition + Constants.ARM_POSITION_MINIMUM)) {
			position = startPosition + Constants.ARM_POSITION_MINIMUM;
		}
		else if ( (desiredPosition + armGetDesiredPosition()) > ( startPosition + Constants.ARM_POSITION_MAXIMUM) ) { 
			position = startPosition + Constants.ARM_POSITION_MAXIMUM;
		}
		else {
			position = desiredPosition;
		}
    ChinUpArm.set(ControlMode.Position, position);
    this.desiredPosition = position;
  }

  public void chinUpMoveToPosition(int desiredPostion) {
    if (armGetCurrentPosition() < desiredPostion) {
      armSetPIDCoefficients(LiftDirection.UP);
    } else {
      armSetPIDCoefficients(LiftDirection.DN);
      chinUpMoveOpenLoop(0);
    }
  }
  
  public void chinUpArmRunOpenLoop(LiftDirection dir, double speed) {
    armSetPIDCoefficients(dir);
    if (!limitChinUp.get()) {
      chinUpMoveOpenLoop(0);
    } else if (dir == LiftDirection.UP) {
      ChinUpArm.set(ControlMode.Position, speed);
    } else {
      ChinUpArm.set(ControlMode.Position, -speed);
    }
  }

  public int armGetDesiredPosition() {
    return desiredPosition;
  }
  
  public int armGetCurrentPosition() {
    return ChinUpArm.getSelectedSensorPosition(0) - startPosition;
  }

  /* Returns true when done */
  public void chinUpFindLimitSwitch() {
    // if (!limitChinUp.get()) {
    //   ChinUpArm.setSelectedSensorPosition(0, 0, 20);
    //   if (!printedZeroing) {
    //     System.out.println("Elevator Zeroing!! Old startPosition " + startPosition + " New startPosition " +  armGetCurrentPosition());
    //     printedZeroing = true;
    //   }
    //   do{
    //     chinUpMoveOpenLoop(-0.2);
    //   } while(!limitChinUp.get());
    //   chinUpMoveOpenLoop(0);
    //   startPosition = armGetCurrentPosition();
    //   return false;
    // } else {
    //   printedZeroing = false;
    //   //chinUpSetPosition(getCurrentPosition());
    //   return false;
    // }
    //liftZero.start();
  }

  public boolean armOnTarget() {
    int cl_err = ChinUpArm.getClosedLoopError(0);
    System.out.println("CL_ERR: " + Math.abs(cl_err) + " motorOut: " + ChinUpArm.getMotorOutputPercent()
    + " Enc: " + ChinUpArm.getSelectedSensorPosition(0) + " startPosition " + startPosition
    + " getDesiredPosition " + armGetDesiredPosition());
    return ChinUpArm.getClosedLoopError(0) < 2000;
  }

  public boolean chinUpOnTarget() {
    int cl_err = ChinUpArm.getClosedLoopError(0);
    System.out.println("CL_ERR: " + Math.abs(cl_err) + " motorOut: " + ChinUpArm.getMotorOutputPercent()
    + " Enc: " + ChinUpArm.getSelectedSensorPosition(0) + " startPosition " + startPosition
    + " getDesiredPosition " + armGetDesiredPosition());
    return ChinUpArm.getClosedLoopError(0) < 2000;
  }

  //RETRACTABLE LEGS

  @Override
  public void initDefaultCommand() {
  }
  public void retractableLegsUp() {
    System.out.println("Bringing RetractableLegs UP.");
    Legs.set(DoubleSolenoid.Value.kReverse);
  }    

  public void retractableLegsDown() {
    System.out.println("Bringing RetractableLegs DOWN.");
    Legs.set(DoubleSolenoid.Value.kForward);
  }

  public int chinUpGetLateralPosition() {
    return LiftChinUpIntake.getSelectedSensorPosition(0) - startPosition;
  }

  //CHIN UP Horizontal Move
  public void chinUpMove(double speed) {
    if(limitChinUp.get()){
      if(speed>0){
        ChinUpArm.set(ControlMode.PercentOutput, speed);
      }
      else {
      ChinUpArm.set(ControlMode.PercentOutput, 0);
      }
    }
    else{
    ChinUpArm.set(ControlMode.PercentOutput, speed);
    }
  }

  public void chinUpMoveOpenLoop(double speed) {
    ChinUpArm.set(ControlMode.PercentOutput, speed);
  }

  public void chinUpMoveToPosition(double position) {
    ChinUpArm.set(ControlMode.Position, position);
  }
  public int getEncoder() {
    return ChinUpArm.getSelectedSensorPosition(0);
  }
}