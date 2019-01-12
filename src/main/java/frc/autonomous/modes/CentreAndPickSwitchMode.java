package frc.autonomous.modes;

import frc.autonomous.AutoBuilder;
import frc.autonomous.AutoCommand;
import frc.autonomous.AutoMode;
//import frc.autonomous.commands.CubeDrop;
import frc.autonomous.commands.CubeTilt;
import frc.autonomous.commands.DriveStraightTalon;
import frc.autonomous.commands.DriveTurn;
import frc.autonomous.commands.ElevatorLift;
import frc.autonomous.commands.KillOffBlockingTalon;
import frc.autonomous.commands.RunIntake;
import frc.autonomous.commands.Wait;
//import frc.robot.CubePickup;
//import frc.robot.DriveTrain;
import frc.robot.Robot;
import frc.robot.CubePickup.IntakeStatus;
//import edu.wpi.first.wpilibj.DriverStation;

public class CentreAndPickSwitchMode extends AutoMode {
//	private DriveTrain driveTrain = DriveTrain.getInstance();

	@Override
	protected AutoCommand[] initializeCommands() {
		String data = Robot.getInstance().getGameSpecificData();
		char switchPosition = data.charAt(0);

		AutoBuilder builder = new AutoBuilder();

		// Pull up intake and drive straight
		builder.add(new CubeTilt('u'));
		builder.add(new DriveStraightTalon(5431));

		if (switchPosition == 'L') {
			System.out.println("Selected left switch mode");
			// Turn and drive straight (turn 1)
			builder.add(new DriveTurn(45));
			builder.add(new DriveStraightTalon(14000));
			// Turn again so we're facing the switch
			builder.add(new DriveTurn(-50));
			// Lift up the elevator to shoot the cube
			builder.add(new ElevatorLift(75000));
			// Drive up to it, non-blocking in case we hit the switch
			builder.add(new DriveStraightTalon(5000, false, DriveStraightTalon.DriveProfile.FAST));
			builder.add(new CubeTilt('m'));
			// Wait to cross the vertical plane of the switch (to avoid fouls)
			builder.add(new Wait(1));
			// Run the intake "in" (out) to make the cube dispense
			builder.add(new RunIntake(IntakeStatus.OUT));
			// Give it three seconds to actually shoot the cube and stop
			builder.add(new Wait(1));
			builder.add(new RunIntake(IntakeStatus.STOP));
			// Stop any driving that was occuring from the driving into the switch
			builder.add(new KillOffBlockingTalon());
			// Back up
			builder.add(new DriveStraightTalon(-10000));
			// Get ready to go pick up the next cube by bringing elevator down
			builder.add(new ElevatorLift(5000));
			// Turn back
			builder.add(new DriveTurn(-90));
//			// Back up at a 45 and turn to face the pyramid
//			builder.add(new DriveStraightTalon(-14600));
//			builder.add(new DriveTurn(-60));
//			builder.add(new CubeTilt('d'));
//			// Run the intake "out" (in) to pick up the cube
//			builder.add(new RunIntake(IntakeStatus.IN));
//			// Drive into the cube
//			builder.add(new DriveStraightTalon(5000, true, DriveStraightTalon.DriveProfile.SLOW));
//			// Stop our intake and back up
//			builder.add(new RunIntake(IntakeStatus.STOP));
//			builder.add(new DriveStraightTalon(-5000));
		}
		else if (switchPosition == 'R') {
			System.out.println("Selected right switch mode");
			builder.add(new DriveTurn(-45));
			builder.add(new DriveStraightTalon(14000));
			builder.add(new DriveTurn(40));
			builder.add(new ElevatorLift(75000));
			builder.add(new DriveStraightTalon(6000, false, DriveStraightTalon.DriveProfile.FAST));
			builder.add(new CubeTilt('m'));
			builder.add(new Wait(1));
			builder.add(new RunIntake(IntakeStatus.OUT));
			builder.add(new Wait(1));
			builder.add(new RunIntake(IntakeStatus.STOP));
			builder.add(new KillOffBlockingTalon());
			builder.add(new DriveStraightTalon(-10000));
			builder.add(new ElevatorLift(5000));
			// Turn back
			builder.add(new DriveTurn(-90));
//			// Back up at a 45 and turn to face the pyramid
//			builder.add(new DriveStraightTalon(-14600));
//			builder.add(new DriveTurn(43));
//			builder.add(new CubeTilt('d'));
//			// Run the intake "out" (in) to pick up the cube
//			builder.add(new RunIntake(IntakeStatus.IN));
//			// Drive into the cube
//			builder.add(new DriveStraightTalon(5000, true, DriveStraightTalon.DriveProfile.SLOW));
//			// Stop our intake and back up
//			builder.add(new RunIntake(IntakeStatus.STOP));
//			builder.add(new DriveStraightTalon(-5000));
////			builder.add(new DriveTurn(-45));
////			builder.add(new DriveStraightTalon(-14500));
////			builder.add(new DriveTurn(40));
////			builder.add(new RunIntake(IntakeStatus.OUT));
////			builder.add(new DriveStraightTalon(5500));
////			builder.add(new RunIntake(IntakeStatus.STOP));
		}
		else {
			// Shouldn't get here, but if we cannot determine which 
			// switch, then just drive forward a given amount of ticks
			// so we at least go over the line
			System.out.println("ERROR: Invalid spec: " + data);
			builder.add(new DriveTurn(-45));
			builder.add(new DriveStraightTalon(14000));
			builder.add(new DriveTurn(40));
		}
		
		return builder.toArray();
	}

}
