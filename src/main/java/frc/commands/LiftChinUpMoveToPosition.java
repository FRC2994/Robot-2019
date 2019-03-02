/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.subsystems.Lift;

/**
 * Drive the given distance straight (negative values go backwards). Uses a
 * local PID controller to run a simple PID loop that is only enabled while this
 * command is running. The input is the averaged values of the left and right
 * encoders.
 */
public class LiftChinUpMoveToPosition extends Command {
  private Lift m_lift = Robot.m_lift;
  private double position;
  private boolean isFinished = false ;

  /**
   * Create a new DriveStraight command.
   * @param position The postion to move to
   */
  public LiftChinUpMoveToPosition(double position) {
    super();
    this.position = position;
    requires(Robot.m_lift);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    m_lift.chinUpMoveToPosition(position);
  }

  // Sets a specfic time until the function stops.
  @Override
  protected void execute() {
     isFinished = m_lift.armOnTarget();
  }

  @Override
  protected boolean isFinished() {
    return isFinished;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }

}