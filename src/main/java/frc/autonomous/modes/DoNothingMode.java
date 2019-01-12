package frc.autonomous.modes;

import frc.autonomous.AutoBuilder;
import frc.autonomous.AutoCommand;
import frc.autonomous.AutoMode;
//import frc.autonomous.commands.RunIntake;
//import frc.robot.CubePickup.IntakeStatus;

public class DoNothingMode extends AutoMode {
	//TODO: Test this on practice field. These are bogus values.

	@Override
	protected AutoCommand[] initializeCommands() {
		// This assumes that we start in the staging zone parallel to the closest scoring platform
		AutoBuilder builder = new AutoBuilder();
		
//		builder.add(new DriveStraightTalon(25000, true));
//		builder.add(new RunIntake(IntakeStatus.IN));
//		builder.add(new CubeDrop(false));
		return builder.toArray();
	}
}
