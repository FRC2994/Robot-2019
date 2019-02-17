/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.subsystems.Subsystems;
import frc.subsystems.DriveTrain;
import frc.subsystems.LED;
import frc.subsystems.LineFollower;
import frc.subsystems.LineFollower.State;
import frc.robot.Robot;

public class FollowLine extends Command {
  private static final double averageSpeed = 0.3;
  private static final double correctionSpeed = 0.2;
  private static final double slowDownSpeed = 0.1;
  private static final DriveTrain drivetrain = Robot.m_drivetrain;
  private static final LineFollower lineFollower = Robot.m_lineFollower;
  private static final LED led = Robot.m_LED;
  private boolean isFinished = false;
  State direction;

  public FollowLine(boolean isNotFinished) {
    requires(lineFollower);
    this.isFinished = !isNotFinished;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    led.setLEDR(false);
    led.setLEDB(false);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
        direction = Robot.m_lineFollower.getState();

        if(direction == State.noneState) {
          //return control to joystick
          isFinished = true;
          System.out.println("Going Nowhere");
        } 
        // else if (direction == State.almostState) {
        //   //Makes robot slow down as it approaches 
        //   drivetrain.tankDrive(slowDownSpeed, slowDownSpeed);
        //   isFinished = false;
        //   System.out.println("Approaching!");
        // }

        // else if (direction == State.finishedState)
        // {
        //   //disable control from joystick
        //   drivetrain.setStopArcadeDrive(true);
        //   isFinished = true;
        //   System.out.println("Finished");
        // }

        else if (direction == State.leftState)
        {
          //disable control from joystick
          drivetrain.setStopArcadeDrive(true);
          //Make robot go Right by increasing left motor speed and decreasing right motor speed
          drivetrain.tankDrive(averageSpeed+correctionSpeed, averageSpeed-correctionSpeed);
          System.out.println("Going Left");
          led.setLEDR(true);
        }

        else if (direction == State.rightState)
        {
          //disable control from joystick
          drivetrain.setStopArcadeDrive(true);
          //Make robot go Left by decreasing left motor speed and increasing right motor speed
          drivetrain.tankDrive(averageSpeed-correctionSpeed, averageSpeed+correctionSpeed);
          System.out.println("Going Right");
          led.setLEDB(true);
        }

        else if (direction == State.centreState)
        {
          //disable control from joystick
          drivetrain.setStopArcadeDrive(true);
          //Keep robot going straight with same speed on both motors
          drivetrain.tankDrive(averageSpeed, averageSpeed);
          System.out.println("Going Straight");
        }

        // else 
        // {
        //   //return control to joystick
        //   isFinished = true;
        // }

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isFinished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
      //return control to joystick
      drivetrain.setStopArcadeDrive(false);
      drivetrain.tankDrive(0,0);
      System.out.println("STOPPED LINE FOLLOWER");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    // isFinished = true;
    // end();
  }
}
