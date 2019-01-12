package frc.autonomous.modes;

import static frc.utils.Constants.GYRO_PID_D;
import static frc.utils.Constants.GYRO_PID_E;
import static frc.utils.Constants.GYRO_PID_I;
import static frc.utils.Constants.GYRO_PID_P;
import static frc.utils.Constants.getConstantAsDouble;

import frc.autonomous.AutoBuilder;
import frc.autonomous.AutoCommand;
import frc.autonomous.AutoMode;
import frc.autonomous.commands.DriveStraightTalon;
import frc.autonomous.commands.DriveTurn;
import frc.utils.SimPID;
import frc.robot.DriveTrain;
import edu.wpi.first.wpilibj.interfaces.Gyro;


public class DriveTrain_Test  extends AutoMode {

	private DriveTrain driveTrain = DriveTrain.getInstance();
	private final int desiredAngle = 22;
	private final double plannedTicks = 2500;
	private final double acceptableTickMargin = 2500;
	private Gyro gyro;

	@Override
	protected AutoCommand[] initializeCommands() 
	{
		AutoBuilder builder = new AutoBuilder();

        /* PART 1 : DriveStraight for a short distance.  Verify left and right side encoders are ok.*/
		builder.add(new DriveStraightTalon(2500, true));
		double LeftEncoderPosition = driveTrain.getLeftEncoderPosition();
		double ticksOff = Math.abs(LeftEncoderPosition - plannedTicks);
		if (ticksOff > acceptableTickMargin) 
		{
			System.out.println("Left Encoders (" + LeftEncoderPosition + ") are out of acceptable margin of " 
			+ acceptableTickMargin + " by " + ticksOff + "ticks.");
		}
	
		double RightEncoderPosition = driveTrain.getRightEncoderPosition();
		ticksOff = Math.abs(RightEncoderPosition - plannedTicks);
		if (ticksOff > acceptableTickMargin) 
		{
			System.out.println("Right Encoders (" + RightEncoderPosition + ") are out of acceptable margin of " 
			+ acceptableTickMargin + " by " + ticksOff + "ticks.");
		}
		
		/* PART 2 : DriveTrun for a small angle.  Verify gyro is ok. */
		// TODO: Change below line 
		builder.add(new DriveStraightTalon(2500, true));
		
		double angle = gyro.getAngle(); // get current heading
		// TODO: Add code to verify against acceptable angle margin. 

		return builder.toArray();
	}
}
