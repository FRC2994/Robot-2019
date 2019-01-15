package frc.subsystems;

public abstract class Subsystem {
	public abstract void initTeleop();
	public abstract void tickTeleop();
	
	public abstract void initAutonomous();
	public abstract void tickAutonomous();
	
	public abstract void initTesting();
	public abstract void tickTesting();
}