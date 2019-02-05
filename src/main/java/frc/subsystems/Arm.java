package frc.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.utils.Constants.*;
import frc.utils.Constants;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PWM;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.IMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;
import frc.robot.Robot;

public class Arm extends Subsystems {
    private static CANSparkMax motor;
    private static VictorSPX hatch;
    private static VictorSPX cargo;
    private static Solenoid hatchPush;

    private static final int armmotorControllerID = 6;
    public static final double armSpeed = 20.0;
    private CANPIDController m_pidController;
    private CANEncoder m_encoder;
    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;
    private int startPoSsition = 0;
    private int positionInTicks = 0;
    private static final int deviceID = 3;
    private static final int DIO_ARM_LIMIT_TOP = 3;
    int startPosition;
    public DigitalInput LimitArmTop;
    private boolean printedZeroing;
    
  
    public Arm() {
  
    // initialize motor
      motor = new CANSparkMax(deviceID, MotorType.kBrushless);
      LimitArmTop = new DigitalInput(DIO_ARM_LIMIT_TOP);
      /**
       * In order to use PID functionality for a controller, a CANPIDController object
       * is constructed by calling the getPIDController() method on an existing
       * CANSparkMax object
       */
      Object m_pidController = motor.getPIDController();
  
      // Encoder object created to display position values
      m_encoder = motor.getEncoder();
  
      // PID coefficients
      kP = 0.1; 
      kI = 1e-4;
      kD = 1; 
      kIz = 0; 
      kFF = 0; 
      kMaxOutput = 1; 
      kMinOutput = -1;
  
      // set PID coefficients
      m_pidController.setP(kP);
      m_pidController.setI(kI);
      m_pidController.setD(kD);
      m_pidController.setIZone(kIz);
      m_pidController.setFF(kFF);
      m_pidController.setOutputRange(kMinOutput, kMaxOutput);
  
       //display PID coefficients on SmartDashboard
       SmartDashboard.putNumber("P Gain", kP);
       SmartDashboard.putNumber("I Gain", kI);
       SmartDashboard.putNumber("D Gain", kD);
       SmartDashboard.putNumber("I Zone", kIz);
       SmartDashboard.putNumber("Feed Forward", kFF);
       SmartDashboard.putNumber("Max Output", kMaxOutput);
       SmartDashboard.putNumber("Min Output", kMinOutput);
       SmartDashboard.putNumber("Set Rotations", 0);
    }
    
    public void setmotorOpenLoop(double percent) {
      // System.out.println("Trying to move Arm in Open Loop... currentPosition " + getDesiredPosition() 
        // + " encPos "  + getDesiredPosition() + " percent " + percent + ".");
      motor.set(percent);
    }
  
    public void setPosition(int positionInTicks) {
      
    // if (positionInTicks < 50 && positionInTicks < this.positionInTicks) {
      //			positionInTicks = 50;
      //		}
      //		System.out.println("Trying to move Arm in Closed Loop... currentPosition " + getDesiredPosition() 
      //			+ " encPos "  + getDesiredPosition() + " positionInTicks " + positionInTicks + ".");
      //		System.out.println("New desired: " + (positionInTicks + startPosition));
      
          motor.set(positionInTicks + startPosition);
          this.positionInTicks = positionInTicks;
        }

    public int getDesiredPosition() {
      return positionInTicks;
    }
    
    public int getRealPosition() {
      
    return motor.getSelectedSensorPosition(0) - startPosition;
    }

    public void stopmotor() {
    //    	setPosition(getDesiredPosition() + Constants.ARM_POSITION_INCREMENT);
    //    	setmotorOpenLoop(0);
      }
    
      public void setPIDUp() {
        /* set closed loop gains in slot0, typically kF stays zero. */
        // motor.config_kF(0, 0.0, 0);
        // motor.config_kP(0, 0.2, 0);
        // motor.config_kI(0, 0.0, 0);
        // motor.config_kD(0, 0.0, 0);
        // motor.configPeakOutputForward(1.0, 0);
      }
      
    
      public void setPIDDown() {
        /* set closed loop gains in slot0, typically kF stays zero. */
        // motor.config_kF(0, 0.0, 0);
        // motor.config_kP(0, 0.1, 0);
        // motor.config_kI(0, 0.0, 0);
        // motor.config_kD(0, 0.0, 0);
        // motor.configPeakOutputReverse(-0.5, 0);
      }
      
      public void zero() {
        if (!LimitArmTop.get()) {
          if (!printedZeroing) {
            System.out.println("Arm Zeroing!! Old startPosition " + startPosition + " New startPosition " +  getRealPosition());
            printedZeroing = true;
          }
          int startPosition = getRealPosition();
          setPosition(0);
        }
        else {
          printedZeroing = false;
          setPosition(getRealPosition());
        }
      }
    
      public void moveDown() {
        if (LimitArmTop.get()) {
          setPIDDown();
          setPosition(getRealPosition() + Constants.Arm_POSITION_DECREMENT);
        } else {
          // System.out.println("Arm Zeroing!!");
          int startPosition = getRealPosition();
          setPosition(0);
          }
        }
    
  
    public void adjustPID() {
      //  read PID coefficients from SmartDashboard
       double p = SmartDashboard.getNumber("P Gain", 0);
       double i = SmartDashboard.getNumber("I Gain", 0);
       double d = SmartDashboard.getNumber("D Gain", 0);
       double iz = SmartDashboard.getNumber("I Zone", 0);
      double ff = SmartDashboard.getNumber("Feed Forward", 0);
      double max = SmartDashboard.getNumber("Max Output", 0);
      double min = SmartDashboard.getNumber("Min Output", 0);
      // double rotations = SmartDashboard.getNumber("Set Rotations", 0);
  
      
      // if PID coefficients on SmartDashboard have changed, write new values to
      // controller
      if((p != kP)) { m_pidController.setP(p); kP = p; }
      if((i != kI)) { m_pidController.setI(i); kI = i; }
      if((d != kD)) { m_pidController.setD(d); kD = d; }
      if((iz != kIz)) { m_pidController.setIZone(iz); kIz = iz; }
      if((ff != kFF)) { m_pidController.setFF(ff); kFF = ff; }
      if((max != kMaxOutput) || (min != kMinOutput)) { 
        m_pidController.setOutputRange(min, max); 
        kMinOutput = min; kMaxOutput = max; 
      }
    }
}