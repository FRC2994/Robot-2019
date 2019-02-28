/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;
import frc.robot.Robot;
import frc.subsystems.GamePieces;
import edu.wpi.first.wpilibj.command.Command;

public class Finger extends Command {

  private static final GamePieces hatch = Robot.m_gamePieces;
  public static enum FinReleaseOrHold { release, hold };
  private FinReleaseOrHold relHold;
  private int counter;
  private static final int counterMax = 25;

  public Finger(FinReleaseOrHold relHold) {
    // Use requires() here to declare subsystem dependencies
    requires(hatch);
    this.relHold = relHold;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    counter = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (relHold == FinReleaseOrHold.hold) {
      if (counter==0) {
        hatch.fingerHold();
     } else if (counter == counterMax) { // 25*20ms = 0.5s
        counter = 0;
      }
    } else {
      if (counter==0) {
         hatch.fingerRelease();
      } else if (counter == counterMax) {
         counter = 0;
      }
    }
    counter++;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return counter==counterMax;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
