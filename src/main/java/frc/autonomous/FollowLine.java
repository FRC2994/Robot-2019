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
import frc.subsystems.DriveTrain;
import frc.subsystems.LineFollower;
import frc.subsystems.LineFollower.State;
import frc.robot.Robot;

public class FollowLine extends Command {
  private static final double averageSpeed = 50;
  private static final double correctionSpeed = 10;
  private static final DriveTrain drivetrain = Robot.drivetrain;
  private static final LineFollower lineFollower = Subsystems.lineFollower;

  public FollowLine() {
    // Use requires() here to declare subsystem dependencies
    //requires(lineFollower);  // TODO uncomment
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
      //return control to joystick
      drivetrain.setStopArcadeDrive(false);
    } 

    else if (direction == State.leftState)
    {
      //disable control from joystick
      drivetrain.setStopArcadeDrive(true);
      //Make robot go Right by increasing left motor speed and decreasing right motor speed
      drivetrain.tankDrive(averageSpeed+correctionSpeed, averageSpeed-correctionSpeed);
    }

    else if (direction == State.rightState)
    {
      //disable control from joystick
      drivetrain.setStopArcadeDrive(true);
      //Make robot go Left by decreasing left motor speed and increasing right motor speed
      drivetrain.tankDrive(averageSpeed-correctionSpeed, averageSpeed+correctionSpeed);
    }

    else if (direction == State.centreState)
    {
      //disable control from joystick
      drivetrain.setStopArcadeDrive(true);
      //Keep robot going straight with same speed on both motors
      drivetrain.tankDrive(averageSpeed, averageSpeed);
    }

    else 
    {
      //return control to joystick
      drivetrain.setStopArcadeDrive(false);
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
      //return control to joystick
      drivetrain.setStopArcadeDrive(false);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
