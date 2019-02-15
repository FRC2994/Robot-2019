/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import javax.xml.namespace.QName;

import edu.wpi.first.wpilibj.command.Command;
import frc.subsystems.LED;
import frc.robot.Robot;

public class controlLED extends Command {
  public int maxCount;
  public int count;
  private static enum modeLED {FAST,SLOW,NORM, OFF};
  private modeLED state;
  private int statusLED = 0;
  private boolean status = false;
  private static final LED led = Robot.m_LED;

  public controlLED(modeLED state) {
    requires(Robot.m_LED);
    this.state = state;
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
      case NORM:  maxCount = 50; //1 Second
                  break;
      case OFF:   status = true;
                  break;
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(count == maxCount){
      if(statusLED == 0) {
        led.setLEDR(false);
        statusLED=1;
      } else if (statusLED == 1) {
        led.setLEDR(true);
        statusLED=0;
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
