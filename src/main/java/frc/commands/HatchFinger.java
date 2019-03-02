/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.subsystems.GamePieces;

public class HatchFinger extends InstantCommand {
  private static final GamePieces finger = Robot.m_gamePieces;
  private static enum fingerStatus {OPEN,CLOSED};
  private fingerStatus currentState;

  public HatchFinger() {
    super();
    requires(finger);
  }

  // Called once when the command executes
  // THIS IS A TOGGLE SYSTEM
  @Override
  protected void initialize() {
    if (currentState == fingerStatus.OPEN) {
      finger.fingerHold();
      currentState = fingerStatus.CLOSED;
    } else {
      finger.fingerRelease();
      currentState = fingerStatus.OPEN;
    }
  }

}
