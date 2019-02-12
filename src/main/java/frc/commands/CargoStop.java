/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.subsystems.GamePieces;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class CargoStop extends InstantCommand {
  private static final GamePieces cargo = Robot.m_gamePieces;
  public CargoStop() {
    super();
    requires(cargo);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    cargo.wheelStop();
    System.out.println("STOP");
  }

}
