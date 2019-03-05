/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.subsystems.GamePieces;


public class HatchPiston extends Command {
  private static final GamePieces piston = Robot.m_gamePieces;
  private boolean isFinished;
  private int count;
  private int maxCount;
  public HatchPiston() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(piston);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    isFinished = false;
    count = 0;
    maxCount = 14; //0.5 seconds
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (count == 0) {
      piston.pistonPush();
      isFinished = false;
    }
    else if (count == maxCount) {
      piston.pistonReset();
      isFinished = true;
    }
    count++;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isFinished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    piston.pistonReset();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
