package frc.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Command;

import frc.utils.Constants;

public class Lift extends Subsystem {
  
  private static final int chinUpPositionIncrement = 0;
  public static enum LiftDirection { UP, DN };
  public static enum LiftPushPullDirection { Push, DN };
  public DigitalInput limitChinUp;
  TalonSRX ChinUpArm;
  TalonSRX ChinUpArm2;
  // VictorSPX LiftChinUpIntake;
  DoubleSolenoid Legs;
  //Command liftZero;
  public int startPosition;
  int desiredPosition;
  boolean printedZeroing;
  public final int kTickIncrement = 5500; //3500
  
  public Lift() {
    limitChinUp = new DigitalInput(Constants.DIO_CHINUP_LIMIT_BOTTOM);
    ChinUpArm = new TalonSRX(Constants.CAN_CHINUP_ARM);
    ChinUpArm2 = new TalonSRX(Constants.CAN_CHINUP_ARM_2);
    // LiftChinUpIntake = new VictorSPX(Constants.CAN_CHINUP_WHEEL_INTAKE);
    Legs = new DoubleSolenoid(Constants.PCM_RETRACTABLE_LEGS1,Constants.PCM_RETRACTABLE_LEGS2);
    ChinUpArm.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);    
  
    System.out.println("Lift Subsystem activated! ");
    resetEncoder();
    retractableLegsUp();

    ChinUpArm2.follow(ChinUpArm);
    ChinUpArm2.setInverted(true);
  }

   //CHIN UP BAR

   private void armSetPIDCoefficients(LiftDirection dir) {
    if (dir == LiftDirection.UP) {
      /* set closed loop gains in slot0, typically kF stays zero. */
      ChinUpArm.config_kF(0, 0.0, 0);
      ChinUpArm.config_kP(0, 0.3, 0);
      ChinUpArm.config_kI(0, 0.0, 0);
      ChinUpArm.config_kD(0, 0.0, 0);	
      ChinUpArm.configPeakOutputForward(1.0, 0);
    } else {
      /* set closed loop gains in slot0, typically kF stays zero. */
      ChinUpArm.config_kF(0, 0.0, 0);
      ChinUpArm.config_kP(0, 0.2, 0);
      ChinUpArm.config_kI(0, 0.0, 0);
      ChinUpArm.config_kD(0, 0.0, 0);
      ChinUpArm.configPeakOutputReverse(-0.5, 0);
    }
  }

  private void chinUpArmSetPosition(int desiredPosition) {
    int position;
    position = desiredPosition;
    ChinUpArm.set(ControlMode.Position, position);
  }
  
  public void moveUp() {
    armSetPIDCoefficients(LiftDirection.UP);
    chinUpArmSetPosition(armGetCurrentPosition() + kTickIncrement);
    // setMotorOpenLoop(-0.4);
    //motor.set(ControlMode.PercentOutput, -0.3);
    System.out.println("MOVING UP");
  }
  
  public void moveDown() {
    if (!limitChinUp.get()) {
      armSetPIDCoefficients(LiftDirection.DN);
      chinUpArmSetPosition(armGetCurrentPosition()-kTickIncrement);
      // setMotorOpenLoop(0.4);
      //motor.set(ControlMode.PercentOutput, 0.4);
      System.out.println("MOVING Down");
    } 
  }

  void resetEncoder() {
    ChinUpArm.setSensorPhase(false);
    ChinUpArm.setSelectedSensorPosition(0);
    ChinUpArm.configClearPositionOnQuadIdx(false,1000);
    startPosition = ChinUpArm.getSelectedSensorPosition();
  }

  public int armGetDesiredPosition() {
    return desiredPosition;
  }
  
  public int armGetCurrentPosition() {
    return ChinUpArm.getSelectedSensorPosition(0);
  }

  public boolean armOnTarget() {
    int cl_err = ChinUpArm.getClosedLoopError(0);
    System.out.println("armOnTarget... CL_ERR " + Math.abs(cl_err) 
    + " motorOutputVoltage " + ChinUpArm.getMotorOutputVoltage() + " motorBusVoltage "  + ChinUpArm.getBusVoltage()
    + " Enc: " + ChinUpArm.getSelectedSensorPosition(0) + " startPosition " + startPosition
    + " getDesiredPosition " + armGetDesiredPosition());
    // return ChinUpArm.getClosedLoopError(0) < 200;
    return true;
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

  public int getEncoder() {
    return ChinUpArm.getSelectedSensorPosition(0);
  }
}