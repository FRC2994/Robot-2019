package frc.utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Constants
{
	// CAN
	public static final int CAN_PCM                    = 8;
	public static final int CAN_RIGHT_FRONT_DRIVE      = 1;
	public static final int CAN_RIGHT_REAR_DRIVE       = 7;
	public static final int CAN_LEFT_FRONT_DRIVE       = 3;
	public static final int CAN_LEFT_REAR_DRIVE        = 4;
	public static final int CAN_CHINUP_WHEEL_INTAKE    = 5;
	public static final int CAN_CHINUP_ARM             = 6;
	public static final int CAN_ARM                    = 10;
	public static final int CAN_WHEEL_INTAKE           = 2;

	// PCM (Solenoid, LEDs, etc...)
	public static final int PCM_GEAR_SHIFT                 = 0;
	public static final int PCM_RETRACTABLE_LEGS           = 1;
	//public static final int PCM_                         = 2; (TBD)
	public static final int PCM_HATCH_FINGER               = 3;
	public static final int PCM_HATCH_PISTON               = 4; 
	public static final int PCM_LED_R                      = 5;
	public static final int PCM_LED_G                      = 6;
	public static final int PCM_LED_B                      = 7;

	// DIO
	public static final int DIO_CUBE_PICKUP            	= 2;
	public static final int DIO_ELEVATOR_LIMIT_BOTTOM  	= 0;
	public static final int DIO_ELEVATOR_LIMIT_TOP     	= 1;
	public static final int DIO_RUS_ECHO			  	= 20;
	public static final int DIO_RUS_TRIG				= 19;
	public static final int DIO_LUS_ECHO				= 24;
	public static final int DIO_LUS_TRIG				= 25;
	public static final int DIO_RIGHT_COLOUR_SENSOR		= 18;
	public static final int DIO_LEFT_COLOUR_SENSOR     	= 23;
	public static final int DIO_ARM_LIMIT_BOTTOM       	= 3;
	public static final int DIO_CHINUP_LIMIT_BOTTOM    	= 4;
	public static final int DIO_WHEEL_INTAKE_LIMIT     	= 5;

	// Analog IO
    //	public static final int AIO_GYRO_SENSOR         = 0;
	public static final int AIO_AUTO_SELECT             = 0;
	public static final int AIO_LEFT_ULTRASONIC_SENSOR  = 25;
	public static final int AIO_RIGHT_ULTRASONIC_SENSOR = 20;

	// USB
	public static final int USB_DRIVE_STICK             = 0;
	public static final int USB_CONTROL_GAMEPAD         = 1;

    // Gamepad
	// public static final int GAMEPAD_PICKUP_IN             = 5; 
	// public static final int GAMEPAD_PICKUP_OUT            = 7; 
    // public static final int GAMEPAD_PICKUP_DOWN           = 1;
    // public static final int GAMEPAD_PICKUP_MID            = 10;
    // public static final int GAMEPAD_PICKUP_UP             = 3; // TODO: pick a button
	// public static final int GAMEPAD_ELEVATOR_DOWN         = 3;
	// public static final int GAMEPAD_ELEVATOR_UP           = 4; 
	// public static final int GAMEPAD_ELEVATOR_FIXED_HEIGHT = 2;
	// public static final int GAMEPAD_CLIMBER_DOWN          = 1; 
	// public static final int GAMEPAD_CLIMBER_THROTTLE      = 4; // Axis... Not a button! 
	
	// ARM Increment constants
	public final static int ARM_ENCODER_TICKS_PER_REV   = 4096;
	public final static int ARM_RPM                     = 2;
//	public final static int ARM_POSITION_INCREMENT      = (int)(ARM_ENCODER_TICKS_PER_REV*TIMED_ROBOT_PERIOD*ELEVATOR_RPM*ELEVATOR_GEAR_FACTOR);
	public final static int ARM_GEAR_FACTOR             = 7;
	public final static int ARM_POSITION_INCREMENT      = 2500*ARM_GEAR_FACTOR;
	public final static int ARM_POSITION_DECREMENT      = -1500*ARM_GEAR_FACTOR;
	public final static int ARM_POSITION_MINIMUM        = 0;
	// 8ft travel * 12in/ft * / (1.5 in * pie) * 4096 ~= 83_000;
	public final static int ARM_POSITION_MAXIMUM        = 300000;   // TODO: 	

	public final static int CLIMBER_GEAR_FACTOR              = 81;
	public final static int CLIMBER_POSITION_INCREMENT       = 1000*CLIMBER_GEAR_FACTOR;
	public final static int CLIMBER_POSITION_DECREMENT       = -1000*CLIMBER_GEAR_FACTOR;

	// Motion Profile
	public static final double METERS_PER_INCHES             = 0.0254;
    public static final double WHEELBASE_WIDTH               = 24*METERS_PER_INCHES;
    // public static final double MOTION_PROFILE_DT          = TIMED_ROBOT_PERIOD;
    public static final double MOTION_PROFILE_MAX_VELOCITY   = 6.0;
    public static final double MOTION_PROFILE_MAX_ACCELERATION = 1;
    public static final double MOTION_PROFILE_MAX_JERK       = 60.0;
    public static final int    ENCODER_TICKS_PER_REVOLUTION  = 128;
    public static final double WHEEL_DIAMETER = 5.875 * METERS_PER_INCHES;
    public static final String MOTION_PROFILE_TRAJECTORIES_LOC = "/home/lvuser/trajectories/";

	// Shooter
	// public static final double INDEXER_SPEED               = 0.5;
	// public static final double CLIMBER_SPEED               = 0.5;
	// public static final double SHOOTER_ENCODER_CALIBRATION = 0.0078125;

	// public static final String CALIBRATION_FILE_LOC ="/home/lvuser/calibration.txt";

}
