package frc.subsystems;

import java.util.ArrayList;
import java.util.List;
import edu.wpi.first.wpilibj.DriverStation;

import static frc.utils.Constants.DIO_ELEVATOR_LIMIT_BOTTOM;
import static frc.utils.Constants.USB_CONTROL_GAMEPAD;
import static frc.utils.Constants.USB_DRIVE_STICK;
import static frc.utils.Constants.AIO_AUTO_SELECT;
import static frc.utils.Constants.getConstantAsInt;
import frc.utils.DriveTrainCharacterizer;
import frc.utils.ArduinoI2C;

import frc.subsystems.Logger;
import frc.controls.EGamepad;
import frc.controls.EJoystick;
import frc.utils.Constants;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PowerDistributionPanel;


public class Subsystems {	

	private static List<Subsystem> subsystemsArray;

	public static Logger Logger = new Logger();

	public static EJoystick	driveJoystick;
	public static EGamepad controlGamepad;
	
	public static PowerDistributionPanel powerPanel;
	public static Compressor compressor;
	public static AnalogInput autoSelectSwitch;

	private static String gameSpecificData = "%NOT POLLED";

	/**
	 * Initialize all of the subsystems, assumes that the constants file has been read already
	 */
	public static void initialize()
	{
		// USB
		driveJoystick = new EJoystick(getConstantAsInt(USB_DRIVE_STICK));
		controlGamepad = new EGamepad(getConstantAsInt(USB_CONTROL_GAMEPAD));

		powerPanel = new PowerDistributionPanel(9);

		compressor = new Compressor(Constants.getConstantAsInt(Constants.PCM_CAN));
		compressor.start();

	    autoSelectSwitch = new AnalogInput(Constants.getConstantAsInt(AIO_AUTO_SELECT));

		Logger.println("Hello World");
		Logger.println("Goodbye Aliens");

		subsystemsArray = new ArrayList<Subsystem>();
		subsystemsArray.add(DriveTrain.getInstance());
	}

	public String getGameSpecificData() {
		return gameSpecificData;
	}

	public static int calcAutoSelectSwitch() {
		return (int)Math.round(autoSelectSwitch.getValue()/(double)360);
	}

	public static void disabledInit() {
		ArduinoI2C A_I2C = new ArduinoI2C();
	}
 
	public static void disabledPeriodic() {
		gameSpecificData = DriverStation.getInstance().getGameSpecificMessage();
	}

	public static void autonomousInit() {
		gameSpecificData = DriverStation.getInstance().getGameSpecificMessage();
		for (Subsystem subsystem : subsystemsArray) {
			subsystem.initAutonomous();
		}
	}   

	/**
	 * This function is called periodically during autonomous
	 */
	public static void autonomousPeriodic() {
		for (Subsystem subsystem : subsystemsArray) {
			subsystem.tickAutonomous();
		}
	}

	public static void teleopInit() {
		for (Subsystem subsystem : subsystemsArray) {
			subsystem.initTeleop();
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	public static void teleopPeriodic() {
		driveJoystick.update();
		controlGamepad.update();
		for (Subsystem subsystem : subsystemsArray) {
			subsystem.tickTeleop();
		}
	}

	public static void testInit() {
		compressor.start();
		for (Subsystem subsystem : subsystemsArray) {
			subsystem.initTesting();
		}
//		dtCharaterizer = new DriveTrainCharacterizer(QUASI_STATIC, Forward);
	}

	/**
	 * This function is called periodically during test mode
	 */
	public static void testPeriodic() {
		driveJoystick.update();
		controlGamepad.update();

		for (Subsystem subsystem : subsystemsArray) {
			subsystem.tickTesting();
		}
	    Logger.addRecord();  // Add the line of accumulated records
	}

}
