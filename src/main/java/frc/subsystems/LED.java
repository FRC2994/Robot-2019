/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.subsystems.DriveTrain;
import frc.subsystems.LineFollower;
import frc.subsystems.Subsystems;
import frc.robot.OI;
import frc.utils.Constants;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;

public class LED extends Subsystems {

	DigitalInput rightColorSensor = new DigitalInput(18);
	DigitalInput leftColorSensor = new DigitalInput(23);
	
	Command autonomousCommand;
	private static LED instance;
	public static DriveTrain m_drivetrain;

	public void LED() {
		LEDR = new Solenoid(Constants.PCM_CAN,Constants.PCM_LED_R);
		LEDG = new Solenoid(Constants.PCM_CAN,Constants.PCM_LED_G);
		LEDB = new Solenoid(Constants.PCM_CAN,Constants.PCM_LED_B);
  }
  public void LED() {
		Subsystems.LED();
		LEDR.set(true);
		LEDG.set(true);
		LEDB.set(true);
	}
}