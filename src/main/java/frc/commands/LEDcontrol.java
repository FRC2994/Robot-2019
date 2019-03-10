/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.subsystems.LED;
import frc.robot.Robot;

public class LEDcontrol extends Command {
  private int maxCount = 50; //Every 1 second
  private int count;
  private boolean LEDstatus = false;
  private static final LED led = Robot.m_LED;

  public enum LEDstate {ON, OFF};
  private LEDstate status;

  public static enum GamePieces {HATCH, CARGO};
  private GamePieces piece;

  private boolean isFinished;

  public LEDcontrol(LEDstate status) {
    requires(led);
    this.status = status;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    if (status == LEDstate.OFF) {
        isFinished = true;
    }
    else {
        if(piece == GamePieces.CARGO) {
            piece = GamePieces.HATCH;

        }
        else{
            piece = GamePieces.CARGO;
        }
        led.setLEDR(false);
        led.setLEDB(false);
        isFinished = false;
        count = 0;
    }
  }

  /*
  RED MEANS CARGO
  BLUE MEANS HATCH
  */

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(count == maxCount){
        if(LEDstatus = false) {
            if(piece == GamePieces.CARGO){
                led.setLEDR(true);
            }
            if(piece == GamePieces.HATCH){
                led.setLEDB(true);
            }
            LEDstatus = true;
        }
        else {
            if(piece == GamePieces.CARGO){
                led.setLEDR(false);
            }
            if(piece == GamePieces.HATCH){
                led.setLEDB(false);
            }
            LEDstatus = false;
        }
        count = 0;
    }
    count++;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
      return isFinished;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    led.setLEDR(false);
    led.setLEDB(false);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
