
package frc.robot;

import java.util.ArrayList;

import java.util.List;

import frc.autonomous.AutoMode;
import frc.autonomous.modes.AutonomousMasterMode;
import frc.autonomous.modes.CentreAndPickSwitchMode;
import frc.autonomous.modes.CrossTheLineMode;
import frc.autonomous.modes.SideToSwitchMode;
import frc.autonomous.modes.SideToScaleMode;
import frc.autonomous.modes.DoNothingMode;
import frc.autonomous.modes.JackTestMode;
import frc.autonomous.modes.FollowMotionProfileMode;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.CameraServer;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;

import frc.utils.Constants;
import frc.utils.DriveTrainCharacterizer;
import frc.utils.ArduinoI2C;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
	private DriveTrain driveTrain;
	private Elevator elevator;
//	private Climber climber;
	private CubePickup cubePickup;
	//private Ramp ramp;
	
	private String gameSpecificData = "%NOT POLLED";
	private static int autoSelectSwitchPosition;
	
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
	
	public static int getAutoSelectSwitchPosition() {
		return autoSelectSwitchPosition;
		
	}
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		this.setPeriod(Constants.TIMED_ROBOT_PERIOD);
		Subsystems.initialize();
		
		subsystems = new ArrayList<Subsystem>();

		this.driveTrain = new DriveTrain();
		subsystems.add(driveTrain);

		this.elevator = new Elevator();
		subsystems.add(elevator);

//		this.climber = new Climber();
//		subsystems.add(climber);
		
		this.cubePickup = new CubePickup();
		subsystems.add(cubePickup);
//		
//		this.ramp = new Ramp();
//		subsystems.add(ramp);
		
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
		elevator.zero();
		gameSpecificData = DriverStation.getInstance().getGameSpecificMessage();
		autoSelectSwitchPosition = Subsystems.calcAutoSelectSwitch();
	}

	AutoMode mode;
	
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
		autoSelectSwitchPosition = Subsystems.calcAutoSelectSwitch();
		System.out.println("autoSelectSwitchPosition : " + autoSelectSwitchPosition + " Raw Value " + Subsystems.autoSelectSwitch.getValue() );
//		mode = new CentreAndPickSwitchMode();
//		mode = new SideToScaleMode();
//		mode = new SideToSwitchMode();
		mode = new FollowMotionProfileMode();
//		mode = new AutonomousMasterMode();
		driveTrain.robotDrive.setSafetyEnabled(false);
		driveTrain.setLowGear();
		driveTrain.zero();
		elevator.zero();
		// pickAModeFromTheAutoSwitch();
		mode.initialize();
	}
	
	private void pickAModeFromTheAutoSwitch() {
		String data = Robot.getInstance().getGameSpecificData();
		char switchPosition = data.charAt(0);
		char scalePosition = data.charAt(1);

		switch (Robot.getAutoSelectSwitchPosition()) {
	       case Constants.AUTOSEL_DONOTHING :
	    	   mode = new DoNothingMode();
	    	   break;
	       case Constants.AUTOSEL_CROSSTHELINE :
	    	   mode = new CrossTheLineMode();
	    	   break;
	       case Constants.AUTOSEL_LEFT_TO_SCALE_SAME_SIDE :
	    	   mode = new SideToScaleMode('L', true);
	    	   break;
	       case Constants.AUTOSEL_LEFT_TO_SCALE_EITHER_SIDE :
	    	   mode = new SideToScaleMode('L', false);
	    	   break;
	       case Constants.AUTOSEL_RIGHT_TO_SCALE_SAME_SIDE :
	    	   mode = new SideToScaleMode('R', true);
	    	   break;
	       case Constants.AUTOSEL_RIGHT_TO_SCALE_EITHER_SIDE :
	    	   mode = new SideToScaleMode('R', false);
	    	   break;
	       case Constants.AUTOSEL_MID_TO_SWITCH_EITHER_SIDE :
	    	   mode = new CentreAndPickSwitchMode();
	    	   break;
	       case Constants.AUTOSEL_LEFT_TO_SWITCH_SAME_SIDE :
	    	   mode = new SideToSwitchMode('L');
	    	   break;
	       case Constants.AUTOSEL_RIGHT_TO_SWITCH_SAME_SIDE :
	    	   mode = new SideToSwitchMode('R');
	    	   break;
	       case Constants.AUTOSEL_LEFT_TO_SCALE_ELSE_SWITCH_SAME_SIDE :
               if (scalePosition == 'L') {
            	   mode = new SideToScaleMode('L',true);
               } else if (switchPosition == 'L') {
            	   mode = new SideToSwitchMode('L');
               } else {
            	   mode = new CrossTheLineMode();
               }
	    	   break;
	       case Constants.AUTOSEL_RIGHT_TO_SCALE_ELSE_SWITCH_SAME_SIDE :
               if (scalePosition == 'R') {
            	   mode = new SideToScaleMode('R', true);
               } else if (switchPosition == 'R') {
            	   mode = new SideToSwitchMode('R');
               } else {
            	   mode = new CrossTheLineMode();
               }
	    	   break;
	       case Constants.AUTOSEL_TEST_MODE :
	    	   mode = new JackTestMode();
	    	   break;
	    };
	    
	    System.out.println("Mode selected: " + mode.getClass().getSimpleName() + " #" + getAutoSelectSwitchPosition());
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		mode.tick();
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
		autoSelectSwitchPosition = Subsystems.calcAutoSelectSwitch();
		System.out.println("autoSelectSwitchPosition : " + autoSelectSwitchPosition + " Raw Value " + Subsystems.autoSelectSwitch.getValue() );
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
		autoSelectSwitchPosition = Subsystems.calcAutoSelectSwitch();
		System.out.println("autoSelectSwitchPosition : " + autoSelectSwitchPosition + " Raw Value " + Subsystems.autoSelectSwitch.getValue() );
	    Subsystems.Logger.addRecord();  // Add the line of accumulated records
	}
}
