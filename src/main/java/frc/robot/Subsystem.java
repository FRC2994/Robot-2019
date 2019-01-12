package frc.robot;

public abstract class Subsystem {
	abstract void initTeleop();

	abstract void tickTeleop();
	
	abstract void tickTesting();

	abstract void initTesting();
}