package frc.autonomous.modes;

//import frc.utils.Constants;
//import frc.utils.Constants.*;
//import frc.robot.Robot;
//import frc.robot.Subsystems;
//import frc.autonomous.AutoBuilder;
import frc.autonomous.AutoCommand;
import frc.autonomous.AutoMode;
//import frc.autonomous.commands.DriveStraightTalon;
//import frc.autonomous.modes.CentreAndPickSwitchMode;
//import frc.autonomous.modes.SideToScaleMode;
//import frc.autonomous.modes.DoNothingMode;
//import frc.autonomous.modes.CrossTheLineMode;
//import frc.autonomous.modes.JackTestMode;

public class AutonomousMasterMode extends AutoMode {
    private static AutoMode mode;

	public AutonomousMasterMode() {
	}
	
	@Override
	protected AutoCommand[] initializeCommands() {
		mode.initialize();
		return null;
//		return mode.initializeCommands();  // TODO: Is this ok?
	};
}

