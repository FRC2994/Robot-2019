package frc.autonomous.modes;

import java.io.File;

import frc.autonomous.AutoBuilder;
import frc.autonomous.AutoCommand;
import frc.autonomous.AutoMode;
import frc.autonomous.commands.*;
import frc.utils.Constants;
//import frc.robot.CubePickup.IntakeStatus;
//import frc.robot.DriveTrain;
//import jaci.pathfinder.Waypoint;

public class JackTestMode extends AutoMode {

//	private DriveTrain driveTrain = DriveTrain.getInstance();

	@Override
	protected AutoCommand[] initializeCommands() {
		AutoBuilder builder = new AutoBuilder();

//		driveTrain.setLowGear();
//		driveTrain.setHighGear();
		
		// GOOD FOR LEFT SWITCH
//		builder.add(new ElevatorLift(150000));
//		builder.add(new DriveStraightTalon(5431));
//		builder.add(new DriveTurn(45));
//		builder.add(new DriveStraightTalon(16500));
//		builder.add(new DriveTurn(-45));
//		builder.add(new DriveStraightTalon(4000, false));
//		builder.add(new Wait(1));
//		builder.add(new CubeDrop(true));
//		builder.add(new RunIntake(IntakeStatus.OUT));
//		builder.add(new Wait(3));
//		builder.add(new RunIntake(IntakeStatus.STOP));
//		builder.add(new KillOffBlockingTalon());
		
		// RIGHT SWITCH
//		builder.add(new ElevatorLift(100000));
//		builder.add(new DriveStraightTalon(5431));
//		builder.add(new DriveTurn(-45));
//		builder.add(new DriveStraightTalon(14000));
//		builder.add(new DriveTurn(45));
//		builder.add(new DriveStraightTalon(6000, false));
//		builder.add(new Wait(1));
//		builder.add(new CubeDrop(true));
//		builder.add(new RunIntake(IntakeStatus.OUT));
//		builder.add(new Wait(3));
//		builder.add(new RunIntake(IntakeStatus.STOP));
//		builder.add(new KillOffBlockingTalon());
		
		
		


//		File leftTraj = new File("/home/lvuser/left.csv");
//		File rightTraj = new File("/home/lvuser/right.csv");
		File leftTraj = new File(Constants.MOTION_PROFILE_TRAJECTORIES_LOC + "MdsLsw_left_detailed.csv");
		File rightTraj = new File(Constants.MOTION_PROFILE_TRAJECTORIES_LOC + "MdsLsw_right_detailed.csv");
		if (!leftTraj.exists()) {
			System.out.println("FollowMotionProfile ERROR: File doesn't exists" + Constants.MOTION_PROFILE_TRAJECTORIES_LOC + "MdsLsw_detailed_left.csv");
		}
		if (!rightTraj.exists()) {
			System.out.println("FollowMotionProfile ERROR: File doesn't exists" + Constants.MOTION_PROFILE_TRAJECTORIES_LOC + "MdsLsw_detailed_right.csv");
		}
		// builder.add(new FollowMotionProfile(leftTraj, rightTraj));

//		Waypoint[] points = new Waypoint[] {
//                new Waypoint(1, 13.5, 0),
//            	new Waypoint(8, 13.5, 0)
//            };
//		builder.add(new FollowMotionProfile(points));

		//builder.add(new ElevatorLift(40000));
		
		return builder.toArray();
	}

}
