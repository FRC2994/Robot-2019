/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.utils.Constants;
import edu.wpi.first.wpilibj.command.Subsystem;

public class LiftIntake extends Subsystem {
  
  VictorSPX LiftChinUpIntake;
  private final double defaultSpeed = 1;

  public LiftIntake() {
    LiftChinUpIntake = new VictorSPX(Constants.CAN_CHINUP_WHEEL_INTAKE);
    LiftChinUpIntake.setNeutralMode(NeutralMode.Brake);
  }

  public void intake() {
    LiftChinUpIntake.set(ControlMode.PercentOutput, defaultSpeed);
  }
  public void outtake() {
    LiftChinUpIntake.set(ControlMode.PercentOutput, -defaultSpeed);
  }
  public void stop() {
    LiftChinUpIntake.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
