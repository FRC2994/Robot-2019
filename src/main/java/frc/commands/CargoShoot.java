/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.subsystems.GamePieces;

public class CargoShoot extends Command {
  private static final GamePieces cargo = Robot.m_gamePieces;

  public CargoShoot() {
    // Use requires() here to declare subsystem dependencies
    requires(cargo);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      //cargo.wheelIntake();
      System.out.println("INTAKING");
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    // cargo.wheelStop();
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("STOP");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
