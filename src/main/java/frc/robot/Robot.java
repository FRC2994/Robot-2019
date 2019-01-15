
package frc.robot;

import java.util.ArrayList;

import java.util.List;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
//import frc.utils.BTMain;
import frc.utils.Constants;
import frc.utils.DriveTrainCharacterizer;
import frc.utils.ArduinoI2C;
import frc.subsystems.DriveTrain;
import frc.subsystems.Subsystem;
import frc.subsystems.Subsystems;

import edu.wpi.first.wpilibj.CameraServer;
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
	private DriveTrain driveTrain;
	
	private String gameSpecificData = "%NOT POLLED";
	
	private List<Subsystem> subsystems;

	private static Robot instance;
	
	public Robot() {
		instance = this;
	}
	
	public static Robot getInstance() {
		return instance;
	}
	
	public String getGameSpecificData() {
		return gameSpecificData;
	}
	
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		// this.setPeriod(Constants.TIMED_ROBOT_PERIOD);
		Subsystems.initialize();
		
		subsystems = new ArrayList<Subsystem>();

		this.driveTrain = new DriveTrain();
		subsystems.add(driveTrain);

		CameraServer.getInstance().startAutomaticCapture();

	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
	   ArduinoI2C A_I2C = new ArduinoI2C();
	}

	@Override
	public void disabledPeriodic() {
		gameSpecificData = DriverStation.getInstance().getGameSpecificMessage();
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
//		Subsystems.btMain.enableRecording(true);
		gameSpecificData = DriverStation.getInstance().getGameSpecificMessage();
		driveTrain.robotDrive.setSafetyEnabled(false);
		driveTrain.setLowGear();
		driveTrain.zero();
	}   

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		// mode.tick();
//		Subsystems.btMain.doRecordingTick();
	}

	@Override
	public void teleopInit() {
		for (Subsystem subsystem : subsystems) {
			subsystem.initTeleop();
		}
		
		Subsystems.driveJoystick.enableButton(3);
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Subsystems.driveJoystick.update();
		Subsystems.controlGamepad.update();

		for (Subsystem subsystem : subsystems) {
			subsystem.tickTeleop();
		}
		
//		Subsystems.btMain.doRecordingTick();
	}

	@Override
	public void testInit() {
		for (Subsystem subsystem : subsystems) {
			subsystem.initTesting();
		}
//		dtCharaterizer = new DriveTrainCharacterizer(QUASI_STATIC, Forward);
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		Subsystems.compressor.start();
		Subsystems.driveJoystick.update();
		Subsystems.controlGamepad.update();

		for (Subsystem subsystem : subsystems) {
			subsystem.tickTesting();
		}
	    // Subsystems.Logger.addRecord();  // Add the line of accumulated records
	}
}
