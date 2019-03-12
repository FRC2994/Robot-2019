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
import edu.wpi.first.wpilibj.Relay;

public class LED extends Subsystem {

	public Relay LEDR, LEDG, LEDB;

	public LED() {
		LEDR = new Relay(Constants.RELAY_LEDR);
        LEDG = new Relay(Constants.RELAY_LEDG);
        LEDB = new Relay(Constants.RELAY_LEDB);
        
        // setLEDR(false);
        // setLEDG(false);
		// setLEDB(false);
		// LEDR.set(Relay.Value.kForward);
    }

	public void setLEDR(boolean value) {
		LEDR.set(value==true?Relay.Value.kOn:Relay.Value.kOff);
	}

	public void setLEDG(boolean value) {
		LEDG.set(value==true?Relay.Value.kOn:Relay.Value.kOff);
	}

	public void setLEDB(boolean value) {
		LEDB.set(value==true?Relay.Value.kOn:Relay.Value.kOff);
	}

	@Override
	protected void initDefaultCommand() {

	}
}