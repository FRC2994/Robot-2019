package frc.autonomous.commands;

import frc.autonomous.AutoCommand;
import edu.wpi.first.wpilibj.Timer;

// A simple command to wait for a couple seconds.
public class Wait implements AutoCommand {
	private double seconds;
	
	public Wait(double seconds) {
		this.seconds = seconds;
	}
	
	@Override
	public void initialize() {
		Timer.delay(seconds);
	}

	@Override
	public boolean tick() {
		return false;
	}

	@Override
	public void cleanup() {
	}
	
}
