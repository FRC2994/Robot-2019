package frc.robot;

import static frc.utils.Constants.DIO_ELEVATOR_LIMIT_BOTTOM;
import static frc.utils.Constants.USB_CONTROL_GAMEPAD;
import static frc.utils.Constants.USB_DRIVE_STICK;
import static frc.utils.Constants.AIO_AUTO_SELECT;
import static frc.utils.Constants.getConstantAsInt;

import frc.controls.EGamepad;
import frc.controls.EJoystick;
import frc.utils.Constants;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.PowerDistributionPanel;


public class Subsystems {	
    // Logger
	public static Logger Logger = new Logger();
	// USB
	public static EJoystick	driveJoystick;
	public static EGamepad controlGamepad;
	
	public static PowerDistributionPanel powerPanel;
	public static Compressor compressor;
	public static Elevator elevator;
//	public static Climber climber;
	public static AnalogInput autoSelectSwitch;
	/**
	 * Initialize all of the subsystems, assumes that the constants file has been read already
	 */
	public static void initialize()
	{
		// USB
		driveJoystick = new EJoystick(getConstantAsInt(USB_DRIVE_STICK));
		controlGamepad = new EGamepad(getConstantAsInt(USB_CONTROL_GAMEPAD));

		// Power Panel
		powerPanel = new PowerDistributionPanel(9);

		//Compressor
		compressor = new Compressor(Constants.getConstantAsInt(Constants.PCM_CAN));
		compressor.start();

        // AutoSelectorSwitch
	    autoSelectSwitch = new AnalogInput(Constants.getConstantAsInt(AIO_AUTO_SELECT));

		Logger.println("Hello World");
		Logger.println("Goodbye Aliens");
	}

	public static int calcAutoSelectSwitch() {
		return (int)Math.round(autoSelectSwitch.getValue()/(double)360);
	}

}
