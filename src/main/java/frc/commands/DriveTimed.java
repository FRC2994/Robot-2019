/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.Command;

import frc.robot.Robot;

import frc.subsystems.DriveTrain;

public class DriveTimed extends Command {
  private final DriveTrain drive = Robot.m_drivetrain;
  private double speed;
  private int time;
  private int maxCount;
  private int count;
  public DriveTimed(double speed, int time) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis)
    requires(drive);
    this.time = time;
    this.speed = speed;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    maxCount = (time*1000)/20;
    count = 0;
    drive.tankDrive(speed,speed);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    count++;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return count==maxCount;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    drive.tankDrive(0,0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
