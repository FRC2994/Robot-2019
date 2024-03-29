/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.subsystems.DriveTrain;
//import frc.subsystems.LED;
import frc.subsystems.LineFollower;
import frc.subsystems.LineFollower.State;
import frc.robot.Robot;

public class FollowLine extends Command {
  private static final double averageSpeed = 0.5;
  private static final double correctionSpeed = 0.5; //0.2
  private static final double slowDownSpeed = 0.3;
  private static final DriveTrain drivetrain = Robot.m_drivetrain;
  private static final LineFollower lineFollower = Robot.m_lineFollower;
  //private static final LED led = Robot.m_LED;
  private boolean isFinished;
  State direction;
  public enum lineGo {GO, STOP};
  public lineGo lineState;

  public FollowLine(lineGo lineState) {
    requires(lineFollower);
    this.lineState = lineState;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // led.setLEDR(false);
    // led.setLEDB(false);
    if(lineState == lineGo.GO){
      isFinished = false;
    }
    else{
      isFinished = true;
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
        direction = lineFollower.getState();

        if(direction == State.noneState) {

          System.out.println("Going Nowhere");
        } 
        // else if (direction == State.almostState) {
        //   //Makes robot slow down as it approaches 
        //   drivetrain.tankDrive(0.3, 0.3);
        //   isFinished = false;
        //   System.out.println("Approaching!");
        // }

        // else if (direction == State.finishedState)
        // {
        //   //disable control from joystick
        //   isFinished = true;
        //   System.out.println("Finished");
        // }
        else if (direction == State.leftState)
        {
          //disable control from joystick
          drivetrain.setStopArcadeDrive(true);
          //Make robot go Right by increasing left motor speed and decreasing right motor speed
          drivetrain.tankDrive(correctionSpeed, slowDownSpeed);
          System.out.println("Going Left");
          // led.setLEDR(true);
          // led.setLEDB(false);
        }

        else if (direction == State.rightState)
        {
          //disable control from joystick
          drivetrain.setStopArcadeDrive(true);
          //Make robot go Left by decreasing left motor speed and increasing right motor speed
          drivetrain.tankDrive(slowDownSpeed, correctionSpeed);
          System.out.println("Going Right");
          // led.setLEDR(false);
          // led.setLEDB(true);
        }

        else if (direction == State.centreState)
        {
          //disable control from joystick
          drivetrain.setStopArcadeDrive(true);
          //Keep robot going straight with same speed on both motors
          drivetrain.tankDrive(0.5, 0.5);
          System.out.println("Going Straight");
          // led.setLEDR(true);
          // led.setLEDB(true);
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
      // led.setLEDB(false);
      // led.setLEDR(false);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    // isFinished = true;
    // end();
  }
}
