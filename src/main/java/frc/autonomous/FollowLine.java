/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.autonomous;

import javax.lang.model.util.ElementScanner6;

import edu.wpi.first.wpilibj.command.Command;
import frc.subsystems.Subsystems;
import frc.subsystems.LineFollower;
import frc.subsystems.LineFollower.State;
import frc.robot.Robot;

public class FollowLine extends Command {
  private static final double averageSpeed = 50;
  private static final double correctionSpeed = 10;
  public FollowLine() {
    // Use requires() here to declare subsystem dependencies
    // requires(Subsystems.lineFollower);  // TODO uncomment
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    State direction = Subsystems.lineFollower.getState();
    if(direction == State.noneState) {
      //
    } 

    else if (direction == State.leftState)
    {
      //Robot Goes Left
      Robot.drivetrain.setMotors(averageSpeed,correctionSpeed);
    }

    else if (direction == State.rightState)
    {
      //Robot Goes Right
      Robot.drivetrain.setMotors(correctionSpeed,averageSpeed);
    }

    else if (direction == State.centreState)
    {
      //Robot Goes Straight
      Robot.drivetrain.setMotors(averageSpeed,averageSpeed);
    }

    else 
    {

    }

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
