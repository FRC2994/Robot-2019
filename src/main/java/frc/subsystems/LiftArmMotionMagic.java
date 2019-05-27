/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import frc.utils.Constants;

public class LiftArmMotionMagic extends Subsystem {
  /*GAIN CONSTANTS*/
  double kP = 0;
  double kI = 0;
  double kD = 0;
  double kF = 0.08; //Formula for calculating kF: 1023/(MaxRPM/Gear Ratio/60*4096)   4096 = QuadEncoder units/rotation
  int cruiseVelocity = 15000;
  int acceleration = 6000;
  int kIncrement = 5500;

  int desiredPosition;

  TalonSRX ChinUpArm;
  TalonSRX ChinUpArm_Slave;
  DigitalInput limitSwitch;

  public LiftArmMotionMagic()
  {
    ChinUpArm = new TalonSRX(Constants.CAN_CHINUP_ARM);
    ChinUpArm_Slave = new TalonSRX(Constants.CAN_CHINUP_ARM_2);
    limitSwitch = new DigitalInput(Constants.DIO_CHINUP_LIMIT_BOTTOM);
    
    ChinUpArm.configNominalOutputForward(0);
		ChinUpArm.configNominalOutputReverse(0);
		ChinUpArm.configPeakOutputForward(1);
		ChinUpArm.configPeakOutputReverse(-1);
    ChinUpArm.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
    ChinUpArm.setInverted(false);
    resetEncoder();
    setPID();
  
    ChinUpArm_Slave.follow(ChinUpArm);
    ChinUpArm_Slave.setInverted(true);
  }

  public void setPID()
  {
    ChinUpArm.selectProfileSlot(0, 0);
		ChinUpArm.config_kF(0, kF, 0);
		ChinUpArm.config_kP(0, kP, 0);
		ChinUpArm.config_kI(0, kI, 0);
    ChinUpArm.config_kD(0, kD, 0);
    ChinUpArm.configMotionCruiseVelocity(cruiseVelocity, 0);
		ChinUpArm.configMotionAcceleration(acceleration, 0);
  }

  public void resetEncoder()
  {
    ChinUpArm.setSensorPhase(false);
    ChinUpArm.setSelectedSensorPosition(0);
    ChinUpArm.configClearPositionOnQuadIdx(false,1000);
  }

  public void moveToPosition(double position)
  {
    ChinUpArm.set(ControlMode.MotionMagic, position);
  }

  public int getPosition()
  {
    return ChinUpArm.getSelectedSensorPosition(0);
  }

  public void moveDown()
  {
    if (!limitSwitch.get()) {
      moveToPosition(getPosition()-kIncrement);
    }
  }

  public void moveUp()
  {
    moveToPosition(getPosition()+kIncrement);
  }

  @Override
  public void initDefaultCommand() {
  }
}
