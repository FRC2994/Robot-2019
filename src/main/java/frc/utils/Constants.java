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

		//PID
		public static final double GYRO_PID_P = 0.03;
		public static final double GYRO_PID_I = 0.01;
		public static final double GYRO_PID_D = 0.01;
		public static final double GYRO_PID_E = 2.0;
		public static final double GYRO_PID_MAX = 0.6;

		public static final double ENCODER_PID_P = 0.5; //2.16 (Ryan and Jacks value
		public static final double ENCODER_PID_I = 0.0;
		public static final double ENCODER_PID_D = 0.0;
		public static final double ENCODER_PID_E = 0.4;
		public static final double ENCODER_PID_MAX = 0.5; // 0.4 (Ryan and Jacks value) Too much jerk

		// CAN
		public static final int CAN_RIGHT_FRONT_DRIVE = 1;
		public static final int CAN_RIGHT_REAR_DRIVE = 7;
		public static final int CAN_LEFT_FRONT_DRIVE = 3;
		public static final int CAN_LEFT_REAR_DRIVE = 4;
		public static final int CAN_PICKUP_RIGHT = 5;
		public static final int CAN_PICKUP_LEFT = 6;
		public static final int CAN_ELEVATOR = 2; // FIXME: Swapped for temporary Talonization of elevator
//		public static final int CAN_CLIMBER = 8; // FIXME: Swapped for temporary Talonization of elevator

		// DIO
		public static final int DIO_CUBE_PICKUP = 2;
		public static final int DIO_ELEVATOR_LIMIT_BOTTOM = 0;
		public static final int DIO_ELEVATOR_LIMIT_TOP = 1;

		public static final double SHOOTER_ENCODER_CALIBRATION = 0.0078125;

		public static final int DIO_RIGHT_COLOUR_SENSOR = 18;
		public static final int DIO_LEFT_COLOUR_SENSOR = 23;

		// Analog IO
//		public static final int AIO_GYRO_SENSOR = 0;
		public static final int AIO_AUTO_SELECT = 0;
		public static final int AIO_LEFT_ULTRASONIC_SENSOR = 25;
		public static final int AIO_RIGHT_ULTRASONIC_SENSOR = 20;

		// USB
		public static final int USB_DRIVE_STICK = 0;
		public static final int USB_CONTROL_GAMEPAD = 1;

		// Solenoid
		public static final int PCM_SHIFTER_A = 0;
		public static final int PCM_SHIFTER_B = 1;


		public static final String CALIBRATION_FILE_LOC ="/home/lvuser/calibration.txt";
		
        // Gamepad
		public static final int GAMEPAD_PICKUP_IN = 5; 
		public static final int GAMEPAD_PICKUP_OUT = 7; 
//		public static final int GAMEPAD_PICKUP_DOWN = 1;
//		public static final int GAMEPAD_PICKUP_MID = 10;
//		public static final int GAMEPAD_PICKUP_UP = 3; // TODO: pick a button
		public static final int GAMEPAD_ELEVATOR_DOWN = 3;
		public static final int GAMEPAD_ELEVATOR_UP = 4; 
		public static final int GAMEPAD_ELEVATOR_FIXED_HEIGHT = 2;
		public static final int GAMEPAD_CLIMBER_DOWN = 1; 
		public static final int GAMEPAD_CLIMBER_THROTTLE = 4; // Axis... Not a button! 

		//Compressor Channel
		public static final int PCM_CAN = 8;
		
		// Double Solenoid Channels
		public static final int SOLENOID_SHIFTER_CHANNEL1 = 0;
		public static final int SOLENOID_PICKUP_1_CHANNEL1 = 1;
		public static final int SOLENOID_PICKUP_1_CHANNEL2 = 2;
		public static final int SOLENOID_PICKUP_2_CHANNEL1 = 3;
		public static final int SOLENOID_PICKUP_2_CHANNEL2 = 4;

		// Shooter
		public static final double INDEXER_SPEED = 0.5;
		public static final double CLIMBER_SPEED = 0.5;
				
		// Motion Profiling
	    //public static final double INCHES_PER_METER,"0.0254;
	    //public static final double WHEELBASE_WIDTH = 0.6;

	    //public static final double MOTION_PROFILE_MAX_VELOCITY = 1.7;
	    //public static final double MOTION_PROFILE_MAX_ACCELERATION = 2.0;

	    //public static final int ENCODER_TICKS_PER_REVOLUTION = 1024;
	    //public static final double WHEEL_DIAMETER = 8.0 * RobotConstants.INCHES_PER_METER;

	
}