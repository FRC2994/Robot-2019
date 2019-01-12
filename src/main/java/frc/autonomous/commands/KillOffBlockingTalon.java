package frc.autonomous.commands;

import frc.autonomous.*;
import frc.robot.DriveTrain;

public class KillOffBlockingTalon implements AutoCommand {
	@Override
	public void initialize() {
		DriveTrain.getInstance().stopTalonPID();
	}

	@Override
	public boolean tick() {
		return false;
	}

	@Override
	public void cleanup() {
		
	}

}
