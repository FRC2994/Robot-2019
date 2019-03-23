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
public class HatchPiston extends InstantCommand {
  private final GamePieces piston = Robot.m_gamePieces;
  public static enum state {PUSHED, RESET};
  private state hatchState;
  public HatchPiston(state hatchState) {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(piston);
    this.hatchState = hatchState;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    if(hatchState == state.PUSHED) {
      piston.pistonPush();
    }
    else if(hatchState == state.RESET) {
      piston.pistonReset();
    }
  }

}
