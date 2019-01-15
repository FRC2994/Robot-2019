package frc.utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Constants
{
	private static final String CONSTANTS_FILE_NAME = 			"/home/lvuser/constants.properties";
	
	private static Properties defaults = new Properties();
	private static Properties constants = new Properties();
	
    public static final double TIMED_ROBOT_PERIOD = 0.020;

    // Elevator Increment constants
	public final static int ELEVATOR_ENCODER_TICKS_PER_REV = 4096;
	public final static int ELEVATOR_RPM = 2;
//	public final static int ELEVATOR_POSITION_INCREMENT = (int)(ELEVATOR_ENCODER_TICKS_PER_REV*TIMED_ROBOT_PERIOD*ELEVATOR_RPM*ELEVATOR_GEAR_FACTOR);
	public final static int ELEVATOR_GEAR_FACTOR = 7;
	public final static int ELEVATOR_POSITION_INCREMENT = 2500*ELEVATOR_GEAR_FACTOR;
	public final static int ELEVATOR_POSITION_DECREMENT = -1500*ELEVATOR_GEAR_FACTOR;
	
	// Elevator minimum/maximum constants
	public final static int ELEVATOR_POSITION_MINIMUM = 0;
	// 8ft travel * 12in/ft * / (1.5 in * pie) * 4096 ~= 83_000;
	public final static int ELEVATOR_POSITION_MAXIMUM = 300000;   // TODO: 	

	public final static int CLIMBER_GEAR_FACTOR = 81;
	public final static int CLIMBER_POSITION_INCREMENT = 1000*CLIMBER_GEAR_FACTOR;
	public final static int CLIMBER_POSITION_DECREMENT = -1000*CLIMBER_GEAR_FACTOR;

	//	Any position: Cross the line only
//	Left   Position: Exchange only
//	Left   Position : Scale or Switch on same side or Exchange
//	Left   Position: Scale or Switch on same side
//	Left   Position: Switch on same side or scale on either side
//	Centre Position: Switch on either side
//	Right Position: Scale or Switch on same side
//	Right Position: Switch on same side or scale on either side

	// Autonomous Selection
	public final static int AUTOSEL_DONOTHING = 0;
	public final static int AUTOSEL_CROSSTHELINE = 1;
	public final static int AUTOSEL_LEFT_TO_SCALE_ELSE_SWITCH_SAME_SIDE = 2; 	
	public final static int AUTOSEL_LEFT_TO_SWITCH_SAME_SIDE = 3; 	
	public final static int AUTOSEL_LEFT_TO_SCALE_SAME_SIDE = 4;
	public final static int AUTOSEL_LEFT_TO_SCALE_EITHER_SIDE = 5; 	
	public final static int AUTOSEL_MID_TO_SWITCH_EITHER_SIDE = 6;
	public final static int AUTOSEL_RIGHT_TO_SCALE_ELSE_SWITCH_SAME_SIDE = 7; 	
	public final static int AUTOSEL_RIGHT_TO_SWITCH_SAME_SIDE = 8; 	
	public final static int AUTOSEL_RIGHT_TO_SCALE_SAME_SIDE = 9;
	public final static int AUTOSEL_RIGHT_TO_SCALE_EITHER_SIDE = 10; 	
	public final static int AUTOSEL_TEST_MODE = 11; 	

	// CAN
	public static final String CAN_RIGHT_FRONT_DRIVE =			"CAN_RIGHT_FRONT_DRIVE";
	public static final String CAN_RIGHT_REAR_DRIVE =			"CAN_RIGHT_REAR_DRIVE";
	public static final String CAN_LEFT_FRONT_DRIVE = 			"CAN_LEFT_FRONT_DRIVE";
	public static final String CAN_LEFT_REAR_DRIVE =			"CAN_LEFT_REAR_DRIVE";
	public static final String CAN_PICKUP_RIGHT = 				"CAN_PICKUP_LEFT";
	public static final String CAN_PICKUP_LEFT = 				"CAN_PICKUP_RIGHT";
	public static final String CAN_ELEVATOR = 					"CAN_ELEVATOR";
	public static final String CAN_CLIMBER = 					"CAN_CLIMBER";
	
	// DIO
	public static final String DIO_CUBE_PICKUP = "DIO_CUBE_PICKUP";
	public static final String DIO_ELEVATOR_LIMIT_BOTTOM =      "DIO_ELEVATOR_LIMIT_BOTTOM";
	public static final String DIO_ELEVATOR_LIMIT_TOP =         "DIO_ELEVATOR_LIMIT_TOP";

	public static final String SHOOTER_ENCODER_CALIBRATION = 	"SHOOTER_ENCODER_CALIBRATION";
	
	// Analog IO
//	public static final String AIO_GYRO_SENSOR =				"AIO_GYRO_SENSOR";
	public static final String AIO_AUTO_SELECT =				"AIO_AUTO_SELECT";

	// USB
	public static final String USB_DRIVE_STICK =				"USB_RIGHT_STICK";
	public static final String USB_CONTROL_GAMEPAD =			"USB_CONTROL_GAMEPAD";
	
	// Solenoids
	public static final String PCM_SHIFTER_A = 					"PCM_SHIFTER_A";
	public static final String PCM_SHIFTER_B = 					"PCM_SHIFTER_B";
	
	//PID
	public static final String GYRO_PID_P = 					"GYRO_PID_P";
	public static final String GYRO_PID_I = 					"GYRO_PID_I";
	public static final String GYRO_PID_D = 					"GYRO_PID_D";
	public static final String GYRO_PID_E = 					"GYRO_PID_E";
	public static final String GYRO_PID_MAX = 					"GYRO_PID_MAX";
	
	public static final String ENCODER_PID_P = 					"ENCODER_PID_P";
	public static final String ENCODER_PID_I = 					"ENCODER_PID_I";
	public static final String ENCODER_PID_D = 					"ENCODER_PID_D";
	public static final String ENCODER_PID_E = 					"ENCODER_PID_E";
	public static final String ENCODER_PID_MAX = 				"ENCODER_PID_MAX";
	
	public static final String CALIBRATION_FILE_LOC =			"CALIBRATION_FILE_LOC";
	public static final String CALIBRATION_BUTTON = 			"CALIBRATION_BUTTON";

	//Compressor channel
	public static final String PCM_CAN =				"PCM_CAN";

	//Double Solenoid Channels
	public static final String SOLENOID_SHIFTER_CHANNEL1 =		"SOLENOID_SHIFTER_CHANNEL1";
	public static final String SOLENOID_PICKUP_1_CHANNEL1 =		"SOLENOID_PICKUP_1_CHANNEL1";
	public static final String SOLENOID_PICKUP_1_CHANNEL2 =		"SOLENOID_PICKUP_1_CHANNEL2";
	public static final String SOLENOID_PICKUP_2_CHANNEL1 =		"SOLENOID_PICKUP_2_CHANNEL1";
	public static final String SOLENOID_PICKUP_2_CHANNEL2 =		"SOLENOID_PICKUP_2_CHANNEL2";
	
	
	// Shooter
	public static final String INDEXER_SPEED = "INDEXER_SPEED";
	public static final String CLIMBER_SPEED = "CLIMBER_SPEED";

	//Gamepad Buttons
	

	//Joystick Buttons
	public static final String JOYSTICK_CALIBRATE  =			"JOYSTICK_CALIBRATE";
	public static final String JOYSTICK_RECORD  =		    	"JOYSTICK_RECORD";
	public static final String JOYSTICK_SHIFTER  =		    	"JOYSTICK_SHIFTER";
	public static final String JOYSTICK_INVERSE  =		    	"JOYSTICK_INVERSE";  // When pressed inverse direction of driveTrain
//	public static final String JOYSTICK_PICKUP_IN =             "JOYSTICK_PICKUP_IN";
//	public static final String JOYSTICK_PICKUP_OUT =            "JOYSTICK_PICKUP_OUT";
	public static final String JOYSTICK_PICKUP_DOWN =           "JOYSTICK_PICKUP_DOWN";
	public static final String JOYSTICK_PICKUP_UP =             "JOYSTICK_PICKUP_UP";
	public static final String JOYSTICK_PICKUP_MID =            "JOYSTICK_PICKUP_MID";
//	public static final String JOYSTICK_ELEVATOR_UP =           "JOYSTICK_ELEVATOR_UP";
//	public static final String JOYSTICK_ELEVATOR_DOWN =         "JOYSTICK_ELEVATOR_DOWN";
	
	// GamePad
	public static final String GAMEPAD_PICKUP_IN =             "GAMEPAD_PICKUP_IN";
	public static final String GAMEPAD_PICKUP_OUT =            "GAMEPAD_PICKUP_OUT";
//	public static final String GAMEPAD_PICKUP_DOWN =           "GAMEPAD_PICKUP_DOWN";
//	public static final String GAMEPAD_PICKUP_MID =            "GAMEPAD_PICKUP_MID";
//	public static final String GAMEPAD_PICKUP_UP =             "GAMEPAD_PICKUP_UP";
	public static final String GAMEPAD_ELEVATOR_DOWN =         "GAMEPAD_ELEVATOR_DOWN";
	public static final String GAMEPAD_ELEVATOR_UP =           "GAMEPAD_ELEVATOR_UP";
	public static final String GAMEPAD_ELEVATOR_FIXED_HEIGHT = "GAMEPAD_ELEVATOR_FIXED_HEIGHT";
	public static final String GAMEPAD_CLIMBER_DOWN =          "GAMEPAD_CLIMBER_DOWN";
	public static final String GAMEPAD_CLIMBER_UP =            "GAMEPAD_CLIMBER_UP";
	public static final String GAMEPAD_CLIMBER_THROTTLE =      "GAMEPAD_CLIMBER_THROTTLE";

	// Motion Profile
	public static final double METERS_PER_INCHES = 0.0254;
    public static final double WHEELBASE_WIDTH = 24*METERS_PER_INCHES;

    public static final double MOTION_PROFILE_DT = TIMED_ROBOT_PERIOD;
    public static final double MOTION_PROFILE_MAX_VELOCITY = 6.0;
    public static final double MOTION_PROFILE_MAX_ACCELERATION = 1;
    public static final double MOTION_PROFILE_MAX_JERK = 60.0;

    public static final int ENCODER_TICKS_PER_REVOLUTION = 128;
    public static final double WHEEL_DIAMETER = 5.875 * METERS_PER_INCHES;
    public static final String MOTION_PROFILE_TRAJECTORIES_LOC = "/home/lvuser/trajectories/";

	static {
		// CAN
		defaults.put(CAN_RIGHT_FRONT_DRIVE, "1");
		defaults.put(CAN_RIGHT_REAR_DRIVE, "7");
		defaults.put(CAN_LEFT_FRONT_DRIVE, "3");
		defaults.put(CAN_LEFT_REAR_DRIVE, "4");
		defaults.put(CAN_PICKUP_RIGHT, "5");
		defaults.put(CAN_PICKUP_LEFT, "6");
		defaults.put(CAN_ELEVATOR, "2"); // FIXME: Swapped for temporary Talonization of elevator
//		defaults.put(CAN_CLIMBER, "8"); // FIXME: Swapped for temporary Talonization of elevator

		// DIO
		defaults.put(DIO_CUBE_PICKUP, "2");
		defaults.put(DIO_ELEVATOR_LIMIT_BOTTOM, "0");
		defaults.put(DIO_ELEVATOR_LIMIT_TOP, "1");

		defaults.put(SHOOTER_ENCODER_CALIBRATION, "0.0078125");

		// Analog IO
//		defaults.put(AIO_GYRO_SENSOR, "0");
		defaults.put(AIO_AUTO_SELECT, "0");

		// USB
		defaults.put(USB_DRIVE_STICK, "0");
		defaults.put(USB_CONTROL_GAMEPAD, "1");

		// Solenoid
		defaults.put(PCM_SHIFTER_A, "0");
		defaults.put(PCM_SHIFTER_B, "1");

		//PID
		defaults.put(GYRO_PID_P, "0.03");
		defaults.put(GYRO_PID_I, "0.01");
		defaults.put(GYRO_PID_D, "0.01");
		defaults.put(GYRO_PID_E, "2.0");
		defaults.put(GYRO_PID_MAX, "0.6");

		defaults.put(ENCODER_PID_P, "0.5"); //2.16 (Ryan and Jacks value)
		defaults.put(ENCODER_PID_I, "0.0");
		defaults.put(ENCODER_PID_D, "0.0");
		defaults.put(ENCODER_PID_E, "0.4");
		defaults.put(ENCODER_PID_MAX, "0.5"); // 0.4 (Ryan and Jacks value) Too much jerk

		defaults.put(CALIBRATION_FILE_LOC, "/home/lvuser/calibration.txt");
		defaults.put(JOYSTICK_CALIBRATE, "11");
		defaults.put(JOYSTICK_RECORD, "4");
		defaults.put(JOYSTICK_SHIFTER, "1");
		defaults.put(JOYSTICK_INVERSE, "2");
//		defaults.put(JOYSTICK_PICKUP_IN, "6");
//		defaults.put(JOYSTICK_PICKUP_OUT, "7");
		defaults.put(JOYSTICK_PICKUP_DOWN, "6");
		defaults.put(JOYSTICK_PICKUP_UP, "7");
		defaults.put(JOYSTICK_PICKUP_MID, "5");
//		defaults.put(JOYSTICK_ELEVATOR_DOWN, "2");
//		defaults.put(JOYSTICK_ELEVATOR_UP, "3");
		
        // Gamepad
		defaults.put(GAMEPAD_PICKUP_IN, "5"); 
		defaults.put(GAMEPAD_PICKUP_OUT, "7"); 
//		defaults.put(GAMEPAD_PICKUP_DOWN, "1");
//		defaults.put(GAMEPAD_PICKUP_MID, "10");
//		defaults.put(GAMEPAD_PICKUP_UP, "3"); // TODO: pick a button
		defaults.put(GAMEPAD_ELEVATOR_DOWN, "3");
		defaults.put(GAMEPAD_ELEVATOR_UP, "4"); 
		defaults.put(GAMEPAD_ELEVATOR_FIXED_HEIGHT, "2");
		defaults.put(GAMEPAD_CLIMBER_DOWN, "1"); 
		defaults.put(GAMEPAD_CLIMBER_THROTTLE, "4"); // Axis... Not a button! 

		//Compressor Channel
		defaults.put(PCM_CAN, "8");
		
		// Double Solenoid Channels
		defaults.put(SOLENOID_SHIFTER_CHANNEL1, "0");
		defaults.put(SOLENOID_PICKUP_1_CHANNEL1, "1");
		defaults.put(SOLENOID_PICKUP_1_CHANNEL2, "2");
		defaults.put(SOLENOID_PICKUP_2_CHANNEL1, "3");
		defaults.put(SOLENOID_PICKUP_2_CHANNEL2, "4");

		// Shooter
		defaults.put(INDEXER_SPEED, "0.5");
		defaults.put(CLIMBER_SPEED, "0.5");
		
		constants.putAll(defaults);
		
		// Motion Profiling
	    //defaults.put(INCHES_PER_METER,"0.0254");
	    //defaults.put(WHEELBASE_WIDTH, "0.6");

	    //defaults.MOTION_PROFILE_MAX_VELOCITY = 1.7;
	    //defaults.MOTION_PROFILE_MAX_ACCELERATION = 2.0;

	    //defaults.ENCODER_TICKS_PER_REVOLUTION = 1024;
	    //defaults.WHEEL_DIAMETER = 8.0 * RobotConstants.INCHES_PER_METER;
	}
	
	/**
	 * Returns constant as a String
	 * @param constant name
	 * @return
	 */
	public static String getConstant(String name) {
		return constants.getProperty(name);
	}

	public static void setConstant(String name, String value) {
		constants.setProperty(name, value);
	}

	/**
	 * Returns constant as an int
	 * @param constant name
	 * @return
	 */
	public static int getConstantAsInt(String name) {
		return Integer.parseInt(constants.getProperty(name));
	}
	
	/**
	 * Returns constant as a double
	 * @param constant name
	 * @return
	 */
	public static double getConstantAsDouble(String name) {
		return Double.parseDouble(constants.getProperty(name));
	}

	public static void readConstantPropertiesFromFile() {
		Properties defaultsFromFile = new Properties();
		FileInputStream in = null;
		try {
			in = new FileInputStream(CONSTANTS_FILE_NAME);			
			defaultsFromFile.load(in);
		} catch (IOException e) {
			System.out.println("Warning: Unable to load properties file " + CONSTANTS_FILE_NAME);
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				System.out.println("Error: Unable to close properties file " + CONSTANTS_FILE_NAME);
				e.printStackTrace();
			}			
		}
		
		constants.putAll(defaultsFromFile);
	}
}