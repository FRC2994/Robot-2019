package frc.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.subsystems.DriveTrain;
import frc.utils.SimLib;
import frc.utils.SimPID;
import frc.robot.Robot; 
import frc.utils.Constants;

//In the process of debugging
public class DriveStraight extends Command {
    
    // Sets the variables for distance and speed
    private final double distance;
    public SimPID drivePID;
	private double maxSpeed;
	private DriveTrain driveTrain = DriveTrain.getInstance();

    //Gets the constant distance
	public DriveStraight(double distance) {
		this(distance, Constants.getConstantAsDouble(Constants.ENCODER_PID_MAX));
    }

    //Gets the total distance at a certain speed.
	public DriveStraight(double distance, double maxSpeed) {
		this.distance = distance;
		this.maxSpeed = maxSpeed;
    }
    
    // Drives straight using drivetrain
    public DriveStraight(){
        requires(Robot.drivetrain);
        setTimeout(1);
    }

    
    private void requires(DriveTrain drivetrain) {
    }

    @Override
    protected void initialize(){
        driveTrain.reset();
		drivePID = driveTrain.getAutoDrivePID();
		drivePID.setDesiredValue(distance+driveTrain.getDistance());
		System.out.println("DriveStraight Init");
		
		int prevEncoder = driveTrain.getRightEncoderValue();
		long prevTime = System.currentTimeMillis();
	}

    //Error on execute(), cannot override in Command
    @Override
    protected void execute() {
        if (!drivePID.isDone()) { // When the drivePID is not done
			double driveVal = drivePID.calcPID(driveTrain.getDistance()); // Gets the distance
			// TODO: Read this from the constants file as "encoderPIDMax"
			double limitVal = SimLib.limitValue(driveVal, maxSpeed);

			System.out.println("Limitval: " + limitVal + ", encoder dist: " + driveTrain.getDistance());
			driveTrain.setMotors(limitVal, limitVal); 

			return true;
		}
		System.out.println("Drive PID Done");
		return false;
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    //Resets the motors
    @Override
    protected void end(){
        driveTrain.setMotors(0.0, 0.0);	
        // Stop
    }

    @Override
    protected void interupted(){
        end();
    }

}
