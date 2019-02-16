package frc.robot;

// import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.TimedRobot;
import frc.commands.Autonomous;
import frc.subsystems.DriveTrain;
import frc.subsystems.LineFollower;
// import frc.subsystems.Logger;
import frc.subsystems.Subsystems;
import frc.subsystems.GamePieces;
import frc.subsystems.LED;
import frc.subsystems.Lift;
import frc.subsystems.Arm;
import frc.robot.OI;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Command;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
// import edu.wpi.first.wpilibj.Counter;
// import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.Ultrasonic;

/**
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {

	Command autonomousCommand;
	private static Robot instance;
	public static DriveTrain m_drivetrain;

	public static OI m_oi;
	
	public static AnalogInput m_autoSelectSwitch;
	public static LineFollower m_lineFollower;
	public static GamePieces m_gamePieces;
	public static LED m_LED;
	public static Lift m_lift;
	public static Arm m_arm;


	public int ledStatus;
	public int count;
	public int maxCount;

	// private static String gameSpecificData = "%NOT POLLED";

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
		m_drivetrain = new DriveTrain();
		m_LED = new LED();
		m_gamePieces = new GamePieces();
		m_lineFollower = new LineFollower();
		CameraServer.getInstance().startAutomaticCapture();	
		Subsystems.initialize();
		autonomousCommand = new Autonomous();
		m_oi = new OI();
		m_arm = new Arm();
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
		// m_lineFollower.run();
	}
	@Override
	public void testInit() {
		Subsystems.testInit();
		ledStatus = 0;
		count = 0;
		maxCount = 25;
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
		if(count == maxCount){
			if(ledStatus == 0) {
				m_LED.setLEDR(true);
				m_LED.setLEDB(false);
				m_LED.setLEDG(false);
				ledStatus = 1;
			} else if (ledStatus == 1) {
				m_LED.setLEDR(false);
				m_LED.setLEDB(true);
				m_LED.setLEDG(false);
				ledStatus = 2;
			} else if (ledStatus == 2) {
				m_LED.setLEDR(false);
				m_LED.setLEDB(false);
				m_LED.setLEDG(true);
				ledStatus = 0;
			}
			count = 0;
		  }
		count = count + 1;
	}
}
