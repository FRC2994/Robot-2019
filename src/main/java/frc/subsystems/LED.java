/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import frc.commands.HatchPiston.state;
import frc.subsystems.Subsystems;
import frc.subsystems.LineFollower.State;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.utils.Constants;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;

public class LED extends Subsystem {

	public DigitalOutput LED_M1_1, LED_M1_2, LED_M1_3, LED_M2_1, LED_M2_2, LED_M2_3;

	public LED() {
		LED_M1_1 = new DigitalOutput(16); //Module 1 | GREEN
		LED_M1_2 = new DigitalOutput(22); //Module 1 | BLUE
		LED_M1_3 = new DigitalOutput(15); //Module 1 | RED
		LED_M2_2 = new DigitalOutput(21); //Module 2 | BLUE
		LED_M2_3 = new DigitalOutput(12); //Module 2 | RED
        
        // setLEDR(false);
        // setLEDG(false);
		// setLEDB(false);
		// LEDR.set(Relay.Value.kForward);
		rightRGB(false, true, false);
		leftRGB(false, false, true);
    }

	public void setLED_M1(boolean one, boolean two, boolean three) { //RIGHT SIDE
		LED_M1_1.set(one); //Green
		LED_M1_2.set(two); //Red
		LED_M1_3.set(three); //Blue
	}


	public void setLED_M2(boolean one, boolean two, boolean three) { //LEFT SIDE
		LED_M2_2.set(two); //Blue
		LED_M2_3.set(three); //Red
	}

	public void rightSideLED(boolean state) {
		// LED_M1_1.set(state);
		setLED_M1(state, false, false);
	}
	public void leftSideLED(boolean state) {
		// LED_M2_3.set(state);
		setLED_M2(false, false, state);
	}

	public void rightRED(boolean state) {
		LED_M1_3.set(state);
	}
	public void rightGREEN(boolean state) {
		LED_M1_1.set(state);
	}
	public void rightBLUE(boolean state) {
		LED_M1_2.set(state);
	}

	public void leftRED(boolean state) {
		LED_M2_3.set(state);
	}
	public void leftGREEN(boolean state) {
		//nothing
	}
	public void leftBLUE(boolean state) {
		LED_M2_2.set(state);
	}

	public void leftRGB(boolean red, boolean green, boolean blue) {
		leftRED(red);
		leftGREEN(green);
		leftBLUE(blue);
	}
	public void rightRGB(boolean red, boolean green, boolean blue) {
		rightRED(red);
		rightGREEN(green);
		rightBLUE(blue);
	}
	@Override
	protected void initDefaultCommand() {

	}
}