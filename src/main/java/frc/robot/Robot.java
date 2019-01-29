package frc.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.subsystems.DriveTrain;
import frc.subsystems.LineFollower;
import frc.subsystems.Logger;
import frc.subsystems.Subsystems;
import frc.utils.Constants;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
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
	public static DriveTrain m_drivetrain;
	public static Logger logger;

	public static OI m_oi;
	
	public static PowerDistributionPanel m_powerPanel;
	public static Compressor m_compressor;
	public static AnalogInput m_autoSelectSwitch;
	public static LineFollower m_lineFollower;

	private static String gameSpecificData = "%NOT POLLED";

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
		CameraServer.getInstance().startAutomaticCapture();	
		m_powerPanel = new PowerDistributionPanel(9);
		m_compressor = new Compressor(Constants.PCM_CAN);
		m_compressor.start();
		m_autoSelectSwitch = new AnalogInput(Constants.AIO_AUTO_SELECT);

		logger = new Logger();
		logger.println("Hello World");
		logger.println("Goodbye Aliens");
	
        // subsysems
		m_drivetrain = new DriveTrain();
		m_lineFollower = new LineFollower();
	}

	public String getGameSpecificData() {
		return gameSpecificData;
	}
	
	public static int calcAutoSelectSwitch() {
		return (int)Math.round(m_autoSelectSwitch.getValue()/(double)360);
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
		autonomousCommand.start();
	}   

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		autonomousCommand.cancel();
	}
// private int loopcounter;
	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		double sensorValue = (sensor.getVoltage()*mVintoV) / scalingFactor;
//loopcounter = ( loopcounter +1) % 100;
		System.out.println("Distance: " + sensorValue + " inches");
//if(loopcounter == 0){		
	//	System.out.println(sensor.getPeriod());
//}
	}

	@Override
	public void testInit() {
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
