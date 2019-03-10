/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;

import frc.utils.Constants;

public class Arm extends Subsystem {
  private DoubleSolenoid arm;

  public Arm() {
    arm = new DoubleSolenoid(Constants.PCM_ARM_OUT, Constants.PCM_ARM_IN);
    arm.set(DoubleSolenoid.Value.kReverse);//Resets the arm
  }

  public void armDown() {
    arm.set(DoubleSolenoid.Value.kForward);
    System.out.println("ARM DOWN");
  }

  public void armUp() {
    arm.set(DoubleSolenoid.Value.kReverse);
    System.out.println("ARM UP");
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
