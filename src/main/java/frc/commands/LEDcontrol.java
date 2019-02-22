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
  public static enum LEDmode {STEADY,FAST,SLOW,OFF};
  public static enum LEDcolor {RED,GREEN,BLUE};
  private int maxCount;
  private int count;
  private LEDmode state;
  private LEDcolor color;
  private boolean status = false;
  private int LEDstatus = 0;
  private static final LED led = Robot.m_LED;

  public LEDcontrol(LEDmode state, LEDcolor color) {
    requires(Robot.m_LED);
    this.state = state;
    this.color = color;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    count = 0;
    switch(state){
      case FAST:  maxCount = 25; //0.5 Second
                  break;
      case SLOW:  maxCount = 200; //2 Second
                  break;
      // case NORM:  maxCount = 50; //1 Second
      //             break;
      case OFF:   status = true;
                  break;
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(count == maxCount){
      if(LEDstatus == 0) {
        if (color == LEDcolor.RED) {
          led.setLEDR(false);
        } else if (color == LEDcolor.GREEN) {
          led.setLEDG(false);
        } else {
          led.setLEDB(false);
        }
        LEDstatus=1;
      } else if (LEDstatus == 1) {
        if (color == LEDcolor.RED) {
          led.setLEDR(true);
        } else if (color == LEDcolor.GREEN) {
          led.setLEDG(true);
        } else {
          led.setLEDB(true);
        }
        LEDstatus=0;
      }
      count = 0;
    }
    count = count + 1;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return status;
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
