package frc.autonomous.modes;

import frc.autonomous.AutoBuilder;
import frc.autonomous.AutoCommand;
import frc.autonomous.AutoMode;
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

public class SideToSwitchMode extends AutoMode {
//	private DriveTrain driveTrain = DriveTrain.getInstance();

	private char startPosition;
    public SideToSwitchMode() {
        this.startPosition = 'R';
    }
    public SideToSwitchMode(char startPosition) {
    	this.startPosition = startPosition;
    }
	@Override
	protected AutoCommand[] initializeCommands() {
		System.out.println("SideToSwitch Started");
		String data = Robot.getInstance().getGameSpecificData();
		char switchPosition = data.charAt(0);
		
        AutoBuilder builder = new AutoBuilder();
		
		if (switchPosition == 'L' && startPosition == 'L') {
			System.out.println("Selected left switch mode friendy side");
			builder.add(new DriveStraightTalon(20000));
			builder.add(new DriveTurn(-45));
			builder.add(new CubeTilt('m'));
			builder.add(new ElevatorLift(100000));
			builder.add(new DriveStraightTalon(4500, false));
			builder.add(new Wait(2));
			builder.add(new RunIntake(IntakeStatus.OUT));
			builder.add(new Wait(3));
			builder.add(new RunIntake(IntakeStatus.STOP));
			builder.add(new KillOffBlockingTalon());
			builder.add(new DriveStraightTalon(-3000));
			builder.add(new ElevatorLift(0));
		}
		else if (switchPosition == 'R' && startPosition == 'R') {
			System.out.println("Selected right switch mode friendy side");
			builder.add(new DriveStraightTalon(20000));
			builder.add(new DriveTurn(40));
			builder.add(new ElevatorLift(100000));
			builder.add(new DriveStraightTalon(4500, false));
			builder.add(new Wait(2));
			builder.add(new RunIntake(IntakeStatus.OUT));
			builder.add(new Wait(3));
			builder.add(new RunIntake(IntakeStatus.STOP));
			builder.add(new KillOffBlockingTalon());
			builder.add(new DriveStraightTalon(-3000));
			builder.add(new ElevatorLift(0));
		}
		else {
			// Shouldn't get here, but if we cannot determine which 
			// switch, then just drive forward a given amount of ticks
			// so we at least go over the line
			System.out.println("ERROR: Invalid spec: " + data);
			builder.add(new DriveStraightTalon(25000));
		}
		
		return builder.toArray();
	}

}
