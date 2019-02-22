package frc.subsystems;


// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.utils.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;

public class Arm extends Subsystem {
    private static TalonSRX motor;
    public static enum ArmMoveDirection { HI, LO };

    private int startPosition = 0;
    private int desiredPosition = 0;
    public DigitalInput LimitArmTop = new DigitalInput(Constants.DIO_ARM_LIMIT_BOTTOM);
    private boolean printedZeroing;
    private final double kError = 1000;
  
    public Arm() {
  
      // initialize motor
      // motor = new CANSparkMax(Constants.CAN_ARM, MotorType.kBrushless);
      motor = new TalonSRX(Constants.CAN_CHINUP_ARM);
      // m_encoder.setPosition(); // TODO: Uncomment when release 1.1 is installed.

      // setPIDCoefficients(0);
    }
    
    private void setPIDCoefficients(ArmMoveDirection dir) {
        if (dir == ArmMoveDirection.HI) {
          /* set closed loop gains in slot0, typically kF stays zero. */
          motor.config_kF(0, 0.0, 0);
          motor.config_kP(0, 0.2, 0);
          motor.config_kI(0, 0.0, 0);
          motor.config_kD(0, 0.0, 0);	
          motor.configPeakOutputForward(1.0, 0);
        } else {
          /* set closed loop gains in slot0, typically kF stays zero. */
          motor.config_kF(0, 0.0, 0);
          motor.config_kP(0, 0.1, 0);
          motor.config_kI(0, 0.0, 0);
          motor.config_kD(0, 0.0, 0);
          motor.configPeakOutputReverse(-0.5, 0);
        }
      }
    
    public void setMotorOpenLoop(double percent) {
      motor.set(ControlMode.Position, percent);
    }
  
    public void setPosition(int positionInTicks) {
      
      // if (positionInTicks < 50 && positionInTicks < this.positionInTicks) {
      //			positionInTicks = 50;
      //		}
      //		System.out.println("Trying to move Arm in Closed Loop... currentPosition " + getDesiredPosition() 
      //			+ " encPos "  + getDesiredPosition() + " positionInTicks " + positionInTicks + ".");
      //		System.out.println("New desired: " + (positionInTicks + startPosition));
      desiredPosition = positionInTicks + startPosition;
      motor.set(ControlMode.Position, desiredPosition);
    }

    public int getDesiredPosition() {
      return desiredPosition;
    }
    
    public int getRealPosition() {
      return (int)motor.getSelectedSensorPosition() - startPosition;
    }

    public void stopMotor() {
        setMotorOpenLoop(0);
    }
    
    public void zero() {
      if (!LimitArmTop.get()) {
        if (!printedZeroing) {
          System.out.println("Arm Zeroing!! Old startPosition " + startPosition + " New startPosition " +  getRealPosition());
          printedZeroing = true;
        }
        startPosition = getRealPosition();
        setPosition(0);
      }
      else {
        printedZeroing = false;
        setPosition(getRealPosition());
      }
    }
    
    public void moveDown() {
      if (LimitArmTop.get()) {
        setPIDCoefficients(ArmMoveDirection.LO);
        setMotorOpenLoop(-0.5);
      } else {
        // System.out.println("Arm Zeroing!!");
        setMotorOpenLoop(0);
        // setPosition(0);
        }
      }
    
    public void moveUp() {
      if (LimitArmTop.get()) {
        setPIDCoefficients(ArmMoveDirection.HI);
        setMotorOpenLoop(0.5);
      } else {
        // System.out.println("Arm Zeroing!!");
        setMotorOpenLoop(0);
        // setPosition(0);
        }
      }
  
      public void moveToPosition(int desiredPostion) {
        if (LimitArmTop.get()) {
          setPIDCoefficients(ArmMoveDirection.HI);
          setPosition(desiredPostion - startPosition);
        } else {
          // System.out.println("Arm Zeroing!!");
          setPosition(desiredPostion);
          }
        }
          
    public boolean onTarget() {
        int cl_err = motor.getClosedLoopError(0);
        System.out.println("CL_ERR: " + Math.abs(cl_err) + " motorOut: " + motor.getMotorOutputPercent()
        + " Enc: " + motor.getSelectedSensorPosition(0) + " startPosition " + startPosition
        + " getDesiredPosition " + getDesiredPosition());
        return motor.getClosedLoopError(0) < kError;
    }
    

  @Override
  protected void initDefaultCommand() {

  }
}
