package frc.autonomous.commands;

import java.io.File;

import frc.autonomous.AutoCommand;
import frc.utils.Constants;
import frc.robot.DriveTrain;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.Segment;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;

// https://github.com/JacisNonsense/Pathfinder/wiki/Pathfinder-for-FRC---Java is a HUGE help here
public final class FollowMotionProfile implements AutoCommand {

    private DriveTrain driveTrain  = DriveTrain.getInstance();
    private EncoderFollower left;
    private EncoderFollower right;
    private static double timePrevious;
    private static int mp_direction = 1;

    public FollowMotionProfile(Waypoint... waypoints) {
        Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, 
        		Trajectory.Config.SAMPLES_HIGH, Constants.MOTION_PROFILE_DT, 
        		Constants.MOTION_PROFILE_MAX_VELOCITY, Constants.MOTION_PROFILE_MAX_ACCELERATION,
        		Constants.MOTION_PROFILE_MAX_JERK);
        Trajectory trajectory = Pathfinder.generate(waypoints, config);
        TankModifier tank = new TankModifier(trajectory).modify(Constants.WHEELBASE_WIDTH);
        this.left = new EncoderFollower(tank.getLeftTrajectory());
        this.right = new EncoderFollower(tank.getRightTrajectory());
    }
    
    public FollowMotionProfile(File left, File right) {
	    	Trajectory trajL = Pathfinder.readFromCSV(left);
	    	Trajectory trajR = Pathfinder.readFromCSV(right);
	    	
	    	this.left = new EncoderFollower(trajL);
	    	this.right = new EncoderFollower(trajR);
    }

    @Override
    public void initialize() {
    	driveTrain.reset();
        left.configureEncoder((int)driveTrain.getLeftEncoderValue(), Constants.ENCODER_TICKS_PER_REVOLUTION, Constants.WHEEL_DIAMETER);
        left.configurePIDVA(0.01, 0, 0, 1 / Constants.MOTION_PROFILE_MAX_VELOCITY, 0);
        right.configureEncoder((int)driveTrain.getRightEncoderValue(), Constants.ENCODER_TICKS_PER_REVOLUTION, Constants.WHEEL_DIAMETER);
        right.configurePIDVA(0.01, 0, 0, 1 / Constants.MOTION_PROFILE_MAX_VELOCITY, 0);

        timePrevious = System.currentTimeMillis();
    }

    @Override
    public boolean tick() {
        double elapsedTime = System.currentTimeMillis() - timePrevious;
    	long codeStartTime = System.currentTimeMillis();
    	
//        if (elapsedTime <= Constants.MOTION_PROFILE_DT * 1000* 4/5) {
//    		return true;
//    	}

        double leftOut = left.calculate(driveTrain.getLeftEncoderValue());
        double rightOut = right.calculate(driveTrain.getRightEncoderValue());

        double gyroError = driveTrain.getHeading() - Math.toDegrees(left.getHeading());
        double correction = mp_direction * 0.03 * Pathfinder.boundHalfDegrees(gyroError);
//        double correction = 0;
        int leftIn = driveTrain.getLeftEncoderValue();
        int rightIn = driveTrain.getRightEncoderValue();

        Segment leftSeg;
        Segment rightSeg;
        if ( !isFinished() ) {
            leftSeg = left.getSegment();
            rightSeg = right.getSegment();
        } else {
        	leftSeg = new Segment(0, 0, 0, 0, 0, 0, 0, 0);
        	rightSeg = new Segment(0, 0, 0, 0, 0, 0, 0, 0);
        }
        
        // if (EncoderExtractor.getSegmentNumber(left) > 100) {
//        	driveTrain.tankDrive(0, 0);
//        	return false;
        // }
        
        long codeElapsed = System.currentTimeMillis() - codeStartTime;

        System.out.println("Time: " + elapsedTime + " Code time: " + codeElapsed + " leftIn: " + leftIn + " rightIn: " + rightIn 
        		+ " leftPos:" + leftSeg.position + " rightPos:" + rightSeg.position 
        		+ " leftVel:" + leftSeg.velocity + " rightVel:" + rightSeg.velocity
        		+ " leftAcc:" + leftSeg.acceleration + " rightAcc:" + rightSeg.acceleration     
        		+ " leftOut: " + leftOut + " rightOut: " + rightOut + " expHeading: " + leftSeg.heading 
        		+ " gyroHeading: " + driveTrain.getHeading() + " correction: " + correction + " lenc: " 
        		+ driveTrain.getLeftEncoderValue() + " renc: " + driveTrain.getRightEncoderValue());

        /*
        System.out.println("Time: " + elapsedTime + " leftIn: " + leftIn + " rightIn: " + rightIn 
        		+ " leftOut: " + leftOut + " rightOut: " + rightOut + " correction: " + correction + " lenc: " + driveTrain.getLeftEncoderValue() + " renc: " + driveTrain.getRightEncoderValue());
*/
        //double leftInWithCorr = mp_direction * (leftIn + correction);
        //double rightInWithCorr = mp_direction * (rightIn - correction);
        //driveTrain.tankDrive(leftInWithCorr, leftInWithCorr);
//        driveTrain.setMotors(leftInWithCorr, rightInWithCorr);
        driveTrain.setMotors(leftIn + correction, -1 * (rightIn - correction));
        timePrevious = System.currentTimeMillis();
        return !isFinished();
    }

    @Override
    public void cleanup() {
        driveTrain.tankDrive(0, 0);
    }

//    @Override
    public boolean isFinished() {
        return left.isFinished() && right.isFinished();
    }

}
