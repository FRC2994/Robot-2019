/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.subsystems.Lift;
import frc.subsystems.Lift.LiftDirection;

public class LiftLegsUpOrDown extends InstantCommand {
  private Lift m_lift = Robot.m_lift;
  private LiftDirection direction;
  public LiftLegsUpOrDown(LiftDirection direction) {
    // Use requires() here to declare subsystem dependencies
    // requires(m_lift); DON"T UNCOMMENT BECAUSE LiftChinupUpOrDown is already doing it.
    this.direction = direction;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if (direction == LiftDirection.DN) {
        m_lift.retractableLegsDown();
    } else {
        m_lift.retractableLegsUp();
    }
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
