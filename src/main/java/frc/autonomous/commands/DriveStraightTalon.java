package frc.autonomous.commands;

import frc.autonomous.AutoCommand;
import frc.robot.DriveTrain;


public class DriveStraightTalon implements AutoCommand {
	public enum DriveProfile {
		FAST,
		SLOW,
		TURBO
	}
	DriveTrain driveTrain = DriveTrain.getInstance();
	int position;
	int talonsHappyCount;
	boolean blocking;
	DriveProfile profile;
	
	public DriveStraightTalon(int position, boolean blocking, DriveProfile profile) {
		this.position = position;
		this.blocking = blocking;
		this.profile = profile;
	}
	
	public DriveStraightTalon(int position, boolean blocking) {
		this(position, blocking, DriveProfile.FAST);
	}
	
	public DriveStraightTalon(int position) {
		this(position, true, DriveProfile.FAST);
	}
	
	@Override
	public void initialize() {
		if (profile == DriveProfile.SLOW) {
			driveTrain.configureSlowProfile();
		} else if (profile == DriveProfile.FAST) {
			driveTrain.configureFastProfile();
		} else if (profile == DriveProfile.TURBO) {
			driveTrain.configureTurboProfile();
		}
		talonsHappyCount = 0;
		driveTrain.zero();
		driveTrain.setDesiredPosition(position);
        System.out.println("DriveStraightTalon starting with desiredPosition " + position + ".");
	}

	@Override
	public boolean tick() {
		// TODO Auto-generated method stub
		if (driveTrain.talonsAreHappy()) {
	        talonsHappyCount++;
		} else {
	        talonsHappyCount = 0;
		}
        boolean isNotDone = blocking && (talonsHappyCount < 5);

//        boolean isNotDone = blocking && (!driveTrain.talonsAreHappy());
//        System.out.println("DriveStraightTalon is not done; iters " + iters + " blocking " + blocking + " driveTrain.talonsAreHappy() " + driveTrain.talonsAreHappy());
        if (isNotDone) {
            System.out.println("DriveStraightTalon is not done; blocking " + blocking + " driveTrain.talonsAreHappy() " + driveTrain.talonsAreHappy() + " talonsHappyCount " + talonsHappyCount );
        } else {
            System.out.println("DriveStraightTalon IS DONE!!!!; blocking " + blocking + " driveTrain.talonsAreHappy() " + driveTrain.talonsAreHappy() + " talonsHappyCount " + talonsHappyCount);
        }
        return isNotDone;
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		System.out.println("DriveStraightTalon Cleanup.  Encoder " + driveTrain.getLeftEncoderPosition());
		driveTrain.zero();
		if (blocking)
			driveTrain.stopTalonPID();
	}

}
