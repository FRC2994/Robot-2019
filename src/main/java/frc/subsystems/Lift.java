package frc.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.utils.Constants;

public class Lift extends Subsystem {
  
  private static final double chinUpSpeed = 0.5;
  public DigitalInput limitChinUp;
  VictorSPX ChinUpPull;
	TalonSRX ChinUpRotation;
  DoubleSolenoid LiftUp;
   
  public Lift() {
    limitChinUp = new DigitalInput(Constants.DIO_CHINUP_LIMIT_BOTTOM);
    ChinUpPull = new VictorSPX(Constants.CAN_CHINUP_PULL);
    ChinUpRotation = new TalonSRX(Constants.CAN_CHINUP_ROTATION);
    LiftUp = new DoubleSolenoid(Constants.PCM_RETRACTABLE_LEG_IN, Constants.PCM_RETRACTABLE_LEG_OUT);
    ChinUpRotation.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
  }
  
  public void setPIDin() {
      /* set closed loop gains in slot0, typically kF stays zero. */
      ChinUpRotation.config_kF(0, 0.0, 0);
      ChinUpRotation.config_kP(0, 0.2, 0);
      ChinUpRotation.config_kI(0, 0.0, 0);
      ChinUpRotation.config_kD(0, 0.0, 0);	
      ChinUpRotation.configPeakOutputForward(1.0, 0);
    }
    
    public void setPIDout() {
      /* set closed loop gains in slot0, typically kF stays zero. */
      ChinUpRotation.config_kF(0, 0.0, 0);
      ChinUpRotation.config_kP(0, 0.1, 0);
      ChinUpRotation.config_kI(0, 0.0, 0);
      ChinUpRotation.config_kD(0, 0.0, 0);
      ChinUpRotation.configPeakOutputReverse(-0.5, 0);
    }

    public void initDefaultCommand() {
    }

    public void PullChinUp() {
      ChinUpPull.set(ControlMode.Position, -chinUpSpeed);
    }

    public void PushChinUp() {
      ChinUpPull.set(ControlMode.Position, chinUpSpeed);
    }

    public void stopChinUpPull() {
      ChinUpPull.set(ControlMode.Position, 0);
    }

    public void chinUpSetPosition(int desiredPosition) {
      ChinUpPull.set(ControlMode.Position, desiredPosition);
    }

    public void initDefaultCom() {
    }
  
    public void Out() {
      ChinUpRotation.set(ControlMode.Position, 1);
      setPIDout();
    }

    public void In() {
      ChinUpRotation.set(ControlMode.Position, -1);
      setPIDin();
    }

    public void stopChinUpRotation() {
    	ChinUpRotation.set(ControlMode.Position, 0);
    }

    public void initDefaultComman() {
    }

    public void LiftUp() {
      System.out.println("Bringing Lift UP.");
      LiftUp.set(Value.kReverse);
    }    

    public void LiftDown() {
      System.out.println("Bringing Lift DOWN.");
      LiftUp.set(Value.kForward);  
      }

  }

