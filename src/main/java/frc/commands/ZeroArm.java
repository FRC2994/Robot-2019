/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.subsystems.Arm;
import frc.robot.Robot;

public class ZeroArm extends Command {
  public final static Arm arm = Robot.m_arm;
  private boolean isFinished;
  public int zero;

  public ZeroArm() {
    // Use requires() here to declare subsystem dependencies
    requires(arm);
    isFinished = false;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if(arm.LimitArmTop.get()){
      isFinished = true;
    } else{
      isFinished = false;
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(arm.LimitArmTop.get()){
      isFinished = true;
      arm.stopMotor();
      System.out.println("ZERO STOPPED!");
    } else{
      isFinished = false;
      arm.move(-0.3);
      System.out.println("ZERO GOING!");
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isFinished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    arm.stopMotor();
    arm.resetEncoder();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    arm.setMotorOpenLoop(0);
  }
}
