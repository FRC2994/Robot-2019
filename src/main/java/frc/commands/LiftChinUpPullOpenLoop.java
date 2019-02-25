/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.subsystems.Lift;
import frc.subsystems.Lift.LiftDirection;
import frc.subsystems.Lift.LiftPushPullDirection;

/**
 * Drive the given distance straight (negative values go backwards). Uses a
 * local PID controller to run a simple PID loop that is only enabled while this
 * command is running. The input is the averaged values of the left and right
 * encoders.
 */
public class LiftChinUpPullOpenLoop extends InstantCommand {
  private Lift m_lift = Robot.m_lift;
  private double speed;

  /**
   * Create a new DriveStraight command.
   * 
   * @param speed The speed to move at
   */
  public LiftChinUpPullOpenLoop(double speed) {
    super();
    this.speed = speed;
    requires(Robot.m_lift);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    m_lift.chinUpMove(speed);
  }

}
