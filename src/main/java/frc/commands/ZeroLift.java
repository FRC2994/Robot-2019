/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.subsystems.Lift;
import frc.robot.Robot;

public class ZeroLift extends Command {
  public final static Lift arm = Robot.m_lift;
  private boolean isFinished;
  public int zero;

  public ZeroLift() {
    // Use requires() here to declare subsystem dependencies
    requires(arm);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if(arm.limitChinUp.get()==true){
      isFinished = true;
    } else{
      isFinished = false;
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(arm.limitChinUp.get()==true){
      isFinished = true;
      arm.chinUpMoveOpenLoop(0);
    } else{
      isFinished = false;
      arm.chinUpMoveOpenLoop(0.1);
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
    arm.chinUpMoveOpenLoop(0);
    zero = arm.getEncoder();
    arm.startPosition = zero;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    arm.chinUpMoveOpenLoop(0);
  }
}
