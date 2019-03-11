/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.subsystems.DriveTrain;
//import frc.subsystems.DriveTrain.driveStatus;
import frc.robot.Robot;

public class DriveReverse extends InstantCommand {
  private final DriveTrain drivetrain = Robot.m_drivetrain;
  private boolean state;
  public DriveReverse(boolean state) {
    super();
    requires(drivetrain);
    this.state = state;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    drivetrain.reverseDrive(state);
  }

}
