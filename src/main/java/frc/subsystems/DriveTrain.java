package frc.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import frc.commands.DriveWithJoystick;
import frc.subsystems.DriveTrain;
import frc.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.utils.Constants;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;

public class DriveTrain extends Subsystem{
    TalonSRX leftFrontDrive = new TalonSRX(Constants.CAN_LEFT_FRONT_DRIVE);
	VictorSPX leftRearDrive = new VictorSPX(Constants.CAN_LEFT_REAR_DRIVE);
	TalonSRX rightFrontDrive = new TalonSRX(Constants.CAN_RIGHT_FRONT_DRIVE);
	VictorSPX rightRearDrive = new VictorSPX(Constants.CAN_RIGHT_REAR_DRIVE);

	Solenoid gearShiftSolenoid = new Solenoid(Constants.CAN_PCM,Constants.PCM_GEAR_SHIFT);
	public static enum GearShiftState { HI, LO };
	//public static enum driveStatus {FORWARD, REVERSE};

	ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	public DifferentialDrive differentialDrive;

	private int startPosition;
	private int desiredPosition = 0;
	private boolean stopArcadeDrive;
	private boolean reverse;
	
    // Circumference in ft = 4in/12(in/ft)*PI
	private static final double leftDistancePerPulse = (4.0 / 12.0 * Math.PI) / 360.0;
	private static final double rightDistancePerPulse = (4.0 / 12.0 * Math.PI) / 360.0;

	public static DriveTrain instance;

	private Joystick joystickValues;
	public class TalonWrapperSpeedController implements SpeedController {
		private TalonSRX talon;
		
		public TalonWrapperSpeedController (TalonSRX talon) {
			this.talon = talon;
		}
		
		@Override
		public void pidWrite(double output) {
			// TODO Auto-generated method stub
		}

		@Override
		public void set(double speed) {
			talon.set(ControlMode.PercentOutput, speed);
		}

		@Override
		public double get() {
			return talon.getMotorOutputPercent();
		}

		@Override
		public void setInverted(boolean isInverted) {
			talon.setInverted(isInverted);
		}

		@Override
		public boolean getInverted() {
			return talon.getInverted();
		}

		@Override
		public void disable() {
			this.set(0);
		}

		@Override
		public void stopMotor() {
			this.disable();			
		}

	}
	
	public static DriveTrain getInstance() {
		return instance;
	}
	
	public void setDesiredPosition(int position) {
		desiredPosition = startPosition - position;
		System.out.println("Setting encoders to " + desiredPosition + " Currently " + (getLeftEncoderValue()));
		leftFrontDrive.set(ControlMode.Position, desiredPosition);
		rightFrontDrive.setInverted(true);
		rightRearDrive.setInverted(true);
		rightFrontDrive.follow(leftFrontDrive);
	}
	
	public int getDesiredPosition() {
		return desiredPosition;
	}

	public void stopTalonPID() {
		leftFrontDrive.set(ControlMode.PercentOutput, 0);
		rightFrontDrive.set(ControlMode.PercentOutput, 0);
		rightRearDrive.setInverted(false);
		rightFrontDrive.setInverted(false);
	}
	
	public boolean onTarget() {
		int cl_err = leftFrontDrive.getClosedLoopError(0);
		System.out.println("CL_ERR: " + Math.abs(cl_err) + " leftMotorOut: " + leftFrontDrive.getMotorOutputPercent() + " rightMotorOut: " + rightFrontDrive.getMotorOutputPercent()
		+ " leftEnc: " + leftFrontDrive.getSelectedSensorPosition(0) + " rightEnc: " + rightFrontDrive.getSelectedSensorPosition(0)
		+ " startPosition " + startPosition);
		return Math.abs(cl_err) < 500;
	}
	
	public void configureTurboProfile() {
		leftFrontDrive.config_kP(0, 0.4, 0);
		leftFrontDrive.config_kD(0, 0.1, 0);
	}
	
	public void configureFastProfile() {
		leftFrontDrive.config_kP(0, 0.2, 0);
		leftFrontDrive.config_kD(0, 0.1, 0);
	}
	
	public void configureSlowProfile() {
		leftFrontDrive.config_kP(0, 0.15, 0);
		leftFrontDrive.config_kD(0, 0, 0);
	}

	public DriveTrain() {
		instance = this;

		// Set the rear drives to follow the left and right front drives
		leftRearDrive.follow(leftFrontDrive);
		rightRearDrive.follow(rightFrontDrive);

        differentialDrive = new DifferentialDrive(new TalonWrapperSpeedController(leftFrontDrive), new TalonWrapperSpeedController(rightFrontDrive));
        // setCurrentLimits();

		gyro.calibrate();

		leftFrontDrive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		leftFrontDrive.setSensorPhase(false);
		rightFrontDrive.setSensorPhase(false);
		rightFrontDrive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);

        // Let's name the sensors on the LiveWindow
        addChild("Drive", differentialDrive);
		addChild("Gyro", gyro);

		rightFrontDrive.setNeutralMode(NeutralMode.Brake);
		rightRearDrive.setNeutralMode(NeutralMode.Brake);
		leftFrontDrive.setNeutralMode(NeutralMode.Brake);
		leftRearDrive.setNeutralMode(NeutralMode.Brake);
		leftFrontDrive.configOpenloopRamp(0.1, 0);
		rightFrontDrive.configOpenloopRamp(0.1, 0);
		leftFrontDrive.enableCurrentLimit(false);
		rightFrontDrive.enableCurrentLimit(false);
		reverse = false;
	}
	
	public void driveWithCurve(double speed, double turn, boolean isQuickTurn) {
		differentialDrive.curvatureDrive(speed, turn, isQuickTurn);
	}
	
	public void arcadeDrive(Joystick driveJoystick) {
        if (!stopArcadeDrive) {
			if(reverse = false){
				differentialDrive.arcadeDrive(driveJoystick.getY(),-driveJoystick.getX());
			}
			else {
				differentialDrive.arcadeDrive(-driveJoystick.getY(),-driveJoystick.getX());
			}
		}
	}

	public void getJoystickValues() {
		joystickValues = Robot.m_oi.getJoystick();
		System.out.println("Y VALUE: " +joystickValues.getY() + " X VALUE: " + joystickValues.getX());
	}
	
	public void tankDrive(double leftSpeed, double rightSpeed) {
		differentialDrive.tankDrive(-leftSpeed, -rightSpeed);
	}
	
	
	public void resetEncoders() {
		leftFrontDrive.setSensorPhase(false);
		rightFrontDrive.setSensorPhase(false);
		leftFrontDrive.configClearPositionOnQuadIdx(false,1000);
		rightFrontDrive.configClearPositionOnQuadIdx(false,1000);
		leftFrontDrive.setSelectedSensorPosition(0,0,0);
		rightFrontDrive.setSelectedSensorPosition(0,0,0);
		if (getLeftEncoderValue() != 0) {
			System.out.println("ERROR - Could not reset Left encoder!!");
		}
		if (getRightEncoderValue() != 0) {
			System.out.println("ERROR - Could not reset Right encoder!!");
		}
	}
	
	public enum BrakeCoastStatus {
		BRAKE,
		COAST
	}
	
	public void setBrakeCoast(BrakeCoastStatus brakeOrCoast) {
		leftRearDrive.setNeutralMode(brakeOrCoast == BrakeCoastStatus.BRAKE ? NeutralMode.Brake : NeutralMode.Coast);
	}

	public void setGear(GearShiftState state) {
	   System.out.println("Trying to shift to gear state " + state);
	   gearShiftSolenoid.set(state==GearShiftState.HI?true:false);
	}

	public void reverseDrive(boolean state) {
		reverse = state;
	}
	
	public void resetGyro() {
		gyro.reset();
	}
	
	public TalonSRX getFrontLeftMotor() {
		return leftFrontDrive;
	}

	public VictorSPX getRearLeftMotor() {
		return leftRearDrive;
	}

	public TalonSRX getFrontRightMotor() {
		return rightFrontDrive;
	}

	public VictorSPX getRearRightMotor() {
		return rightRearDrive;
	}

	public int getLeftEncoderValue() {
		return leftFrontDrive.getSelectedSensorPosition(0);
	}

	public int getRightEncoderValue() {
		return rightFrontDrive.getSelectedSensorPosition(0);
	}
	public void zero() {
		startPosition = getLeftEncoderValue();
	}

    public void	setStopArcadeDrive(boolean value) {
		stopArcadeDrive = value;
	}

	public void setCurrentLimits() {
		leftFrontDrive.configContinuousCurrentLimit(30, 0);
		rightFrontDrive.configContinuousCurrentLimit(30, 0);

		leftFrontDrive.configPeakCurrentLimit(40, 0);
		rightFrontDrive.configPeakCurrentLimit(40, 0);
		
		leftFrontDrive.configPeakCurrentDuration(100, 0);
		rightFrontDrive.configPeakCurrentDuration(100, 0);

		leftFrontDrive.enableCurrentLimit(false);
		rightFrontDrive.enableCurrentLimit(false);
				
		leftFrontDrive.configOpenloopRamp(0, 0);
		rightFrontDrive.configOpenloopRamp(0, 0);
	}

    /**
     * This function is called periodically by Scheduler.run
     */
	@Override
	public void periodic() {
//		System.out.println("Left Encoder: " + getLeftEncoderValue() +" Right Encoder: " + getRightEncoderValue());
		// Logger.appendRecord(
		//  		getFrontLeftMotor().getMotorOutputVoltage() + "\t" + getFrontRightMotor().getMotorOutputVoltage() + 
		//  		"\t" + getLeftEncoderValue() + "\t" + getRightEncoderValue() + "\t" + getHeading() + "\t");
	}

   /**
    * Get the robot's heading.
    *
    * @return The robots heading in degrees.
    */
    public double getHeading() {
      return gyro.getAngle();
    }

   /**
    * Reset the robots sensors to the zero states.
    */
    public void reset() {
        setDefaultCommand(new DriveWithJoystick());
		resetEncoders();
		resetGyro();
		stopArcadeDrive = false;
		rightRearDrive.setInverted(false);
		rightFrontDrive.setInverted(false);
		// rightRearDrive.setInverted(true);
		// rightFrontDrive.setInverted(true);
		//setBrakeCoast(BrakeCoastStatus.BRAKE);
		setGear(GearShiftState.LO);
		zero();
        Logger.appendRecord("dtLmtr\tdtRmtr\tdtLenc\tdtRenc\tdtGyro\t");
    }

   /**
    * Get the average distance of the encoders since the last reset.
    *
    * @return The distance driven (average of left and right encoders).
    */
    public double getDistance() {
	   return (getLeftEncoderValue()*leftDistancePerPulse + 
	         getRightEncoderValue()*rightDistancePerPulse) / 2;
    }

   /**
    * When no other command is running let the operator drive around using the
    * PS3 joystick.
    */
    @Override
    public void initDefaultCommand() {
		reset();
	}

}
