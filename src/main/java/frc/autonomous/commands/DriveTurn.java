package frc.autonomous.commands;

import frc.autonomous.AutoCommand;
import frc.utils.SimLib;
import frc.utils.SimPID;
import frc.robot.DriveTrain;
//import frc.robot.Subsystems;

public class DriveTurn implements AutoCommand {

	private final int angle;
	private SimPID gyroPID;
	private DriveTrain driveTrain = DriveTrain.getInstance();
	
	public DriveTurn(int angle) {
		this.angle = angle;
	}
	
	@Override
	public void initialize() {
		driveTrain.reset();
		gyroPID = driveTrain.getTurnPID();
		gyroPID.setDesiredValue(angle);
		System.out.println("DriveTurn Init : angle " + angle + ".");
	}
	
	@Override
	public boolean tick() {
		if (!gyroPID.isDone()) {
			double driveVal = gyroPID.calcPID(-driveTrain.getHeading());
			System.out.println("Gyro: " + driveTrain.getHeading() + "L: " + driveVal);
			double limitVal = SimLib.limitValue(driveVal, 1);
			driveTrain.setMotors(limitVal, -limitVal);
			return true;
		}
		return false;
	}

	@Override
	public void cleanup() {
		System.out.println("DriveTurn Cleanup");
		driveTrain.setMotors(0.0, 0.0);
	}
	
}
