/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.subsystems.LED;

public class LEDTest extends Command {
  private static final LED led = Robot.m_LED;
  int maxCount = 50;
  int count;
  private static enum status {ONE, TWO, THREE, OFF};
  status ledState;
  public LEDTest() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(led);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    led.setLED_M1(false, false, false);
    count = 0;
    led.setLED_M1(true, false, false);
    ledState = status.ONE;
    System.out.println("BUTTON PRESSED");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(count == maxCount) {
      if(ledState == status.ONE) {
        led.setLED_M1(false, true, false);
        System.out.println("TWO");
        ledState = status.TWO;
      }
      else if (ledState == status.TWO){
        led.setLED_M1(false, false, true);
        System.out.println("THREE");
        ledState = status.THREE;
      }
      else if (ledState == status.THREE) {
        led.setLED_M1(true, false, false);
        System.out.println("ONE");
        ledState = status.ONE;
      }
      count = 0;
    }
    count++;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
