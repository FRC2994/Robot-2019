/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import frc.subsystems.Subsystems;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.utils.Constants;
import edu.wpi.first.wpilibj.Solenoid;

public class LED extends Subsystem {

	Solenoid LEDR, LEDG, LEDB;

	public LED() {
		LEDR = new Solenoid(Constants.CAN_PCM,Constants.PCM_LED_R);
		LEDG = new Solenoid(Constants.CAN_PCM,Constants.PCM_LED_G);
		LEDB = new Solenoid(Constants.CAN_PCM,Constants.PCM_LED_B);
    }

	public void setLEDR(boolean value) {
		LEDR.set(value);
	}

	public void setLEDG(boolean value) {
		LEDG.set(value);
	}

	public void setLEDB(boolean value) {
		LEDB.set(value);
	}

	@Override
	protected void initDefaultCommand() {

	}
}