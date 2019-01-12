package frc.autonomous.modes;

import frc.autonomous.AutoBuilder;
import frc.autonomous.AutoCommand;
import frc.autonomous.AutoMode;
import frc.autonomous.commands.DriveStraightTalon;

public class CrossTheLineMode extends AutoMode {

	@Override
	protected AutoCommand[] initializeCommands() {
		AutoBuilder builder = new AutoBuilder();
		builder.add(new DriveStraightTalon(25000, true));
//		builder.add(new RunIntake(IntakeStatus.IN));
//		builder.add(new CubeDrop(false));
		return builder.toArray();
	}
}
