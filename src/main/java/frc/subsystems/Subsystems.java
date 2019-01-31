package frc.subsystems;

import java.util.ArrayList;
import java.util.List;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.AnalogInput;

import static frc.utils.Constants.DIO_ELEVATOR_LIMIT_BOTTOM;
import static frc.utils.Constants.USB_CONTROL_GAMEPAD;
import static frc.utils.Constants.USB_DRIVE_STICK;
import static frc.utils.Constants.AIO_AUTO_SELECT;
import frc.utils.DriveTrainCharacterizer;
import frc.utils.ArduinoI2C;

import frc.subsystems.Logger;
import frc.subsystems.LineFollower;
import frc.robot.Robot;
import frc.utils.Constants;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PowerDistributionPanel;


public class Subsystems {	

	public static Logger logger = new Logger();
	
	public static PowerDistributionPanel powerPanel;
	public static Compressor compressor;
	public static AnalogInput autoSelectSwitch;
	// public static LineFollower lineFollower;

	private static String gameSpecificData = "%NOT POLLED";

	/**
	 * Initialize all of the subsystems, assumes that the constants file has been read already
	 */
	public static void initialize()
	{
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
	}   

	/**
	 * This function is called periodically during autonomous
	 */
	public static void autonomousPeriodic() {
	    logger.addRecord();  // Add the line of accumulated records
	}

	public static void teleopInit() {
	}

	/**
	 * This function is called periodically during operator control
	 */
	public static void teleopPeriodic() {
	    logger.addRecord();  // Add the line of accumulated records
	}

	public static void testInit() {
//		dtCharaterizer = new DriveTrainCharacterizer(QUASI_STATIC, Forward);
	}

	/**
	 * This function is called periodically during test mode
	 */
	public static void testPeriodic() {
	    logger.addRecord();  // Add the line of accumulated records
	}

}
