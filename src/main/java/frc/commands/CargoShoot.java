/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.subsystems.GamePieces;

public class CargoShoot extends InstantCommand {
  private static final GamePieces cargo = Robot.m_gamePieces;

  public CargoShoot() {
    // Use requires() here to declare subsystem dependencies
    requires(cargo);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    cargo.wheelShoot();
    System.out.println("SHOOT");
  }
}
