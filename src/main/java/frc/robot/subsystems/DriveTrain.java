package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import static main.java.frc.utils.Constants.CAN_LEFT_FRONT_DRIVE;
import static main.java.frc.utils.Constants.CAN_LEFT_REAR_DRIVE;
import static main.java.frc.utils.Constants.CAN_RIGHT_FRONT_DRIVE;
import static main.java.frc.utils.Constants.CAN_RIGHT_REAR_DRIVE;
import static main.java.frc.utils.Constants.ENCODER_PID_D;
import static main.java.frc.utils.Constants.ENCODER_PID_E;
import static main.java.frc.utils.Constants.ENCODER_PID_I;
import static main.java.frc.utils.Constants.ENCODER_PID_P;
import static main.java.frc.utils.Constants.GYRO_PID_D;
import static main.java.frc.utils.Constants.GYRO_PID_E;
import static main.java.frc.utils.Constants.GYRO_PID_I;
import static main.java.frc.utils.Constants.GYRO_PID_P;
import static main.java.frc.utils.Constants.PCM_CAN;
import static main.java.frc.utils.Constants.SOLENOID_SHIFTER_CHANNEL1;
import static main.java.frc.utils.Constants.JOYSTICK_SHIFTER;
import static main.java.frc.utils.Constants.JOYSTICK_INVERSE;
import static main.java.frc.utils.Constants.getConstantAsDouble;
import static main.java.frc.utils.Constants.getConstantAsInt;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import main.java.frc.controls.ButtonEntry;
import main.java.frc.controls.EJoystick;
import main.java.frc.utils.Constants;
import main.java.frc.utils.SimPID;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;

public class DriveTrain extends Subsystem{
    TalonSRX leftFrontDrive = new TalonSRX(getConstantAsInt(CAN_LEFT_FRONT_DRIVE));
	TalonSRX leftRearDrive = new TalonSRX(getConstantAsInt(CAN_LEFT_REAR_DRIVE));
	TalonSRX rightFrontDrive = new TalonSRX(getConstantAsInt(CAN_RIGHT_FRONT_DRIVE));
	VictorSPX rightRearDrive = new VictorSPX(getConstantAsInt(CAN_RIGHT_REAR_DRIVE));

    
	ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	RobotDrive robotDrive;
	SimPID gyroPID = new SimPID(
						getConstantAsDouble(GYRO_PID_P),
						getConstantAsDouble(GYRO_PID_I),
						getConstantAsDouble(GYRO_PID_D),
						getConstantAsDouble(GYRO_PID_E));

	SimPID autoDrivePID = new SimPID(
						getConstantAsDouble(ENCODER_PID_P),
						getConstantAsDouble(ENCODER_PID_I),
						getConstantAsDouble(ENCODER_PID_D),
						getConstantAsDouble(ENCODER_PID_E));
	
	Solenoid gearShiftSolenoid = new Solenoid(getConstantAsInt(PCM_CAN), 
											getConstantAsInt(SOLENOID_SHIFTER_CHANNEL1));
	private int startPosition;
	private int desiredPosition = 0;

	public static DriveTrain instance;
	
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
	
	public boolean talonsAreHappy() {
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
		// Set the rear drives to follow the left and right front drives
		leftRearDrive.follow(leftFrontDrive);
		rightRearDrive.follow(rightFrontDrive);

//		rightFrontDrive.config_kP(0, 0.3, 0);

		robotDrive = new RobotDrive(new TalonWrapperSpeedController(leftFrontDrive), new TalonWrapperSpeedController(rightFrontDrive));

//		rightDriveEncoder.setDistancePerPulse(0.026);
//		leftDriveEncoder.setDistancePerPulse(0.001);
//		rightDriveEncoder.setReverseDirection(true);
//		leftDriveEncoder.setReverseDirection(true);

		gyro.calibrate();

		// Set low gear by default
		setLowGear();

		leftFrontDrive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
		leftFrontDrive.setSensorPhase(false);
		rightFrontDrive.setSensorPhase(false);
		rightFrontDrive.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

		instance = this;
	}
	
	public void reset() {
		autoDrivePID.setDesiredValue(0);
		// Reset the encoder PID to a reasonable state.
		autoDrivePID.resetErrorSum();
		autoDrivePID.resetPreviousVal();
		// Used to make sure that the PID doesn't bail out as done
		// right away (we know both the distances are zero from the
		// above reset).
		autoDrivePID.calcPID(0);

		gyroPID = new SimPID(
				getConstantAsDouble(GYRO_PID_P),
				getConstantAsDouble(GYRO_PID_I),
				getConstantAsDouble(GYRO_PID_D),
				getConstantAsDouble(GYRO_PID_E));
		
		this.resetEncoders();
		this.resetGyro();
	}
	
	public void driveWithCurve(double speed, double turn) {
		robotDrive.drive(speed, turn);
	}
	
	public void arcadeDrive(EJoystick driveJoystick) {
		//differentiaDrive.arcadeDrive(driveJoystick.getMagnitude(), driveJoystick.getDirectionRadians());
		robotDrive.arcadeDrive(driveJoystick);
	}
	
	public void tankDrive(double leftValue, double rightValue) {
		robotDrive.tankDrive(leftValue, rightValue);
	}
	
	
	public void resetEncoders() {
		leftFrontDrive.setSelectedSensorPosition(0,0,0);
		rightFrontDrive.setSelectedSensorPosition(0,0,0);
		if (getLeftEncoderValue() != 0) {
			//System.out.println("ERROR - Could not reset Left encoder!!");
		}
		if (getRightEncoderValue() != 0) {
			//System.out.println("ERROR - Could not reset Right encoder!!");
		}
		 
		//TODO: Reverse encoders on talons - how do you do this
	}
	
	public enum BrakeCoastStatus {
		BRAKE,
		COAST
	}
	
	public void setBrakeCoast(BrakeCoastStatus brakeOrCoast) {
		leftRearDrive.setNeutralMode(brakeOrCoast == BrakeCoastStatus.BRAKE ? NeutralMode.Brake : NeutralMode.Coast);
	}

	public void setLowGear() {
		System.out.println("Trying to shift to low gear.");
		gearShiftSolenoid.set(false);
	}

	public void setHighGear() {
//		System.out.println("Trying to shift to high gear.");
		gearShiftSolenoid.set(true);
	}
	
	public double getHeading() {
		return gyro.getAngle();
	}
	
	public void resetGyro() {
		gyro.reset();
	}
	
	public SimPID getAutoDrivePID() {
		return autoDrivePID;
	}

	public SimPID getTurnPID() {
		return gyroPID;
	}
	
	
	public TalonSRX getFrontLeftMotor() {
		return leftFrontDrive;
	}

	public TalonSRX getRearLeftMotor() {
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
	
	public double getLeftEncoderPosition() {
		return getLeftEncoderValue();
	}

	public double getRightEncoderPosition() {
		return getRightEncoderValue();
	}

	public double getDistance() {
		return getLeftEncoderValue();
		//TODO: Get encoder position from Talons and average it
	}
	
	@Override
	public void initTeleop() {
		zero();
		rightRearDrive.setInverted(false);
		rightFrontDrive.setInverted(false);
		// Set low gear & coast mode by default
		setBrakeCoast(BrakeCoastStatus.BRAKE);
		setLowGear();
		reset();

		robotDrive.setSafetyEnabled(false);
		driveJoystick.enableButton(Constants.getConstantAsInt(JOYSTICK_SHIFTER));
		driveJoystick.enableButton(Constants.getConstantAsInt(JOYSTICK_INVERSE));

        // Logger.appendRecord("dtLmtr\tdtRmtr\tdtLenc\tdtRenc\tdtGyro");
}

	@Override
	public void tickTeleop() {
		if (driveJoystick.getEvent(Constants.getConstantAsInt(JOYSTICK_SHIFTER)) == ButtonEntry.EVENT_CLOSED) {
			setHighGear();
		}
		if (driveJoystick.getEvent(Constants.getConstantAsInt(JOYSTICK_SHIFTER)) == ButtonEntry.EVENT_OPENED) {
			setLowGear();
		}
		
		if (driveJoystick.getEvent(Constants.getConstantAsInt(JOYSTICK_INVERSE)) == ButtonEntry.EVENT_CLOSED) {
//			rightRearDrive.setInverted(false);
//			rightFrontDrive.setInverted(false);
//			leftRearDrive.setInverted(true);
//			leftFrontDrive.setInverted(true);
			// Set the rear drives to follow the left and right front drives
//			leftRearDrive.follow(leftFrontDrive);
//			rightRearDrive.follow(rightFrontDrive);
		}
		
		if (driveJoystick.getEvent(Constants.getConstantAsInt(JOYSTICK_INVERSE)) == ButtonEntry.EVENT_OPENED) {
//			rightRearDrive.setInverted(true);
//			rightFrontDrive.setInverted(true);
//			leftRearDrive.setInverted(false);
//			leftFrontDrive.setInverted(false);
			// Set the rear drives to follow the left and right front drives
//			leftRearDrive.follow(leftFrontDrive);
//			rightRearDrive.follow(rightFrontDrive);
		}
		
//		System.out.println("Left Encoder: " + getLeftEncoderValue() +" Right Encoder: " + getRightEncoderValue());
		robotDrive.arcadeDrive(driveJoystick);
		// Logger.appendRecord(
		// 		getFrontLeftMotor().getMotorOutputVoltage() + "\t" + getFrontRightMotor().getMotorOutputVoltage() + 
		// 		"\t" + getLeftEncoderValue() + "\t" + getRightEncoderValue() + "\t" + getHeading());
	}

	@Override
	public void tickTesting() {
		
	}

	@Override
	public void initTesting() {
		
	}

	public void setMotors(double leftMotors, double rightMotors) {
//		differentialDrive.drive(leftMotors, rightMotors);
		robotDrive.setLeftRightMotorOutputs(leftMotors, rightMotors);
	}

	public void zero() {
		startPosition = getLeftEncoderValue();
	}
}