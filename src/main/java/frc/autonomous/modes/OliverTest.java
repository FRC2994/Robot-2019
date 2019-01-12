package frc.autonomous.modes;

import java.io.File;

import edu.wpi.first.wpilibj.command.Command;
import frc.autonomous.AutoBuilder;
import frc.autonomous.AutoCommand;
// import frc.autonomous.AutoMode;
import frc.autonomous.commands.FollowMotionProfile;
import frc.autonomous.commands.DriveStraight;
import frc.autonomous.commands.DriveTurn;
import frc.autonomous.commands.Wait;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.SwerveModifier;
import frc.utils.SimLib;
import frc.utils.SimPID;


public class OliverTest extends Command {

    public OliverTest() {
        super("OliverTest");

    }
    @Override
	protected void initialize() {
		// This assumes that we start in the staging zone parallel to the closest scoring platform
        AutoBuilder builder = new AutoBuilder();

		Waypoint[] points = new Waypoint[] {
                new Waypoint(1, 13.5, Pathfinder.d2r(0)),
            	new Waypoint(11, 18, 0)
            };
		builder.add(new FollowMotionProfile(points));
        
        Trajectory.Config config = new Trajectory.Config(Trajectory.FitMethod.HERMITE_CUBIC, Trajectory.Config.SAMPLES_HIGH, 0.05, 1.7, 2.0, 60.0);
        Trajectory trajectory = Pathfinder.generate(points, config);

        SwerveModifier modifier = new SwerveModifier(trajectory).modify(0.5, 0.5, SwerveModifier.Mode.SWERVE_DEFAULT);
        EncoderFollower frontLeft = new EncoderFollower(modifier.getFrontLeftTrajectory());
        EncoderFollower frontRight = new EncoderFollower(modifier.getFrontRightTrajectory());
        EncoderFollower backLeft = new EncoderFollower(modifier.getBackLeftTrajectory());
        EncoderFollower backright = new EncoderFollower(modifier.getBackRightTrajectory());
        
        // Encoder Position is the current, cumulative position of your encoder. If you're using an SRX, this will be the
        // 'getEncPosition' function.
        // 1000 is the amount of encoder ticks per full revolution
        // Wheel Diameter is the diameter of your wheels (or pulley for a track system) in meters
        frontLeft.configureEncoder(getEncPosition, 1000, wheel_diameter);
        
        // The first argument is the proportional gain. Usually this will be quite high
        // The second argument is the integral gain. This is unused for motion profiling
        // The third argument is the derivative gain. Tweak this if you are unhappy with the tracking of the trajectory
        // The fourth argument is the velocity ratio. This is 1 over the maximum velocity you provided in the 
        //      trajectory configuration (it translates m/s to a -1 to 1 scale that your motors can read)
        // The fifth argument is your acceleration gain. Tweak this if you want to get to a higher or lower speed quicker
        frontLeft.configurePIDVA(1.0, 0.0, 0.0, 1 / max_velocity , 0);

        File leftFile = new File("left.csv");
		File rightFile = new File("right.csv");
		builder.add(new FollowMotionProfile(leftFile, rightFile));

	}

    protected void execute(){

    }
    @Override
    protected boolean isFinished() {
        return false;
    }

}