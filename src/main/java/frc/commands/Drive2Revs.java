/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.subsystems.DriveTrain;

/**
 * Add your docs here.
 */
public class Drive2Revs extends InstantCommand {
  DriveTrain drive = Robot.m_drivetrain;
  int ticksPerRev = 3800;
  /**
   * Add your docs here.
   */
  public Drive2Revs() {
    super();
    requires(drive);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    drive.moveToPosition(ticksPerRev*2);
  }

}
