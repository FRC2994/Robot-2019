
package frc.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.subsystems.DriveTrain;
import frc.subsystems.Subsystems;
import frc.utils.Constants;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalSource;

/**
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {

	AnalogInput sensor = new AnalogInput(2);
	// DigitalInput sensorDIO = new DigitalInput(3);
	int mVintoV = 1000;
	double scalingFactor = 0.009766 * mVintoV;
	// Counter sensor = new Counter(sensorDIO);
	
	Command autonomousCommand;
	private static Robot instance;
	public static DriveTrain drivetrain;

	public Robot() {
		instance = this;
	}
	
	public static Robot getInstance() {
		return instance;
	}
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		// this.kDefaultPeriod = Constants.TIMED_ROBOT_PERIOD;
		drivetrain = new DriveTrain();
		CameraServer.getInstance().startAutomaticCapture();	
		Subsystems.initialize();
		// autonomousCommand = new Autonomous();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
	   Subsystems.disabledInit();
	}

	@Override
	public void disabledPeriodic() {
		Subsystems.disabledPeriodic();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 * 
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		Subsystems.autonomousInit();
		autonomousCommand.start();
	}   

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Subsystems.autonomousPeriodic();
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		Subsystems.teleopInit();
		//autonomousCommand.cancel();
	}
private int loopcounter;
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		double sensorValue = (sensor.getVoltage()*mVintoV) / scalingFactor;

		Subsystems.teleopPeriodic();
		Scheduler.getInstance().run();
//loopcounter = ( loopcounter +1) % 100;
		System.out.println("Distance: " + sensorValue + " inches");
//if(loopcounter == 0){		
	//	System.out.println(sensor.getPeriod());
//}
	}

	@Override
	public void testInit() {
		Subsystems.testInit();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		Subsystems.testPeriodic();
		LiveWindow.run();
	}
}
