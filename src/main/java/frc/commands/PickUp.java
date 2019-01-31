/*
package frc.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;

import static frc.utils.Constants.*;
import frc.subsystems.Subsystem;
import frc.controls.EJoystick;
import frc.controls.ButtonEntry;
import static frc.subsystems.Subsystems.driveJoystick;
// import frc.subsystems.Subsystems;
import frc.subsystems.DriveTrain;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import frc.controls.ButtonEntry;
import frc.controls.EJoystick;
import frc.utils.Constants;
import frc.utils.SimPID;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.RobotDrive;
//import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;


public class PickUp extends Command {

    private VictorSPX WheelIntake;
 	private IntakeStatus intakeStatus = IntakeStatus.STOP;
    private PickupStatus  = pickupStatusckupStatus.UP;
    

    @Override
    protected void initializeArm() {

        public enum PickupStatus {
            UP,
            MID,
            DOWN,
        }
        
        public enum IntakeStatus {
            STOP,
            OUT,
            IN,
        }
        
        public void setPickupStatus(PickupStatus status) {
            switch (status) {
            case UP:
                tiltPickupUp();
                break;
            case DOWN:
                tiltPickupDown();
                break;
            case MID:
                tiltPickupMid();
                break;
            }
    
            pickupStatus = status;
        }
    
        public void setIntakeStatus(IntakeStatus status) {
            switch (status) {
            case STOP:
                pickupStop();
                break;
            case OUT:
                pickupOut();
                break;
            case IN:
                pickupIn();
                break;
            }
            
            intakeStatus = status;
        }
        
    }

    @Override
    protected void intializeStatus() {
                
        public void pickupArm(double value) {
            pickupArm.set(ControlMode.PercentOutput, value);
        }
        
        public void tiltPickupUp() {
           System.out.println("Trying to tilt pickup UP.");
           //spark.set(Value.kReverse);
           //spark.set(Value.kReverse);
        }

        public void tiltPickupDown() {
            System.out.println("Trying to tilt pickup DOWN.");
            //spark.set(Value.kForward);
            //spark.set(Value.kForward);
        }
        
        public void tiltPickupMid() {
            System.out.println("Trying to tilt pickup MID.");
            //spark.set(Value.kForward);
            //spark.set(Value.kReverse);
        }

        public void pickupOut() {
            pickupArm(-0.5);
        }

        public void pickupIn() {
            pickupArm(0.5);
        }
        
        public void pickupInFast() {
            pickupArm(0.5);
        }

        public void pickupStop() {
            pickupArm(0);
        }

    @Override
    protected void executeCargo() {

        


    }

    @Override
    protected boolean isFinished() {
    return false;
    }


    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }

}
*/