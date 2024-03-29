/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.subsystems.LED;
public class LEDTESTALL extends InstantCommand {
  public final LED led = Robot.m_LED;
  public LEDTESTALL() {
    super();
    // Use requires() here to declare subsystem dependencies
    requires(led);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    led.rightRGB(true, true, true);
		led.leftRGB(true, true, true);
  }

}
