package frc.subsystems;


import edu.wpi.first.wpilibj.command.Subsystem;
import frc.utils.Constants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.ControlType;

import com.revrobotics.CANError;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ArmSparkMax extends Subsystem{
    private static CANSparkMax motor;
    private CANPIDController m_pid;
    private CANEncoder m_encoder;

    public static enum Direction 
    {
        UP, 
        DOWN
    };

    private Direction direction;
    private DigitalInput limitBot = new DigitalInput(10);
    
    public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;
    private double proportional, integral, derivative;
    private double desiredPosition = 0;

    private static final double kIncrement = 100;
    private static final double kLimitTick = 1000;
    double currentPosition, lastPosition;
    int[] setpoints = {50, 100, 527, 200};

    double[] PID_UP = {0.067, 0.00012, 0.0};
    double[] PID_DOWN = {0.08, 0, 0.0001};

    int PID_UPSLOT = 0;
    int PID_DOWNSLOT = 1;

    public ArmSparkMax() {
        motor = new CANSparkMax(10, MotorType.kBrushless);
        m_pid = motor.getPIDController();
        m_encoder = motor.getEncoder();

        motor.restoreFactoryDefaults();
        motor.setIdleMode(CANSparkMax.IdleMode.kBrake);

        direction = Direction.UP;
        resetEncoder();

        setPIDCoefficients(direction);
    }
    
    private void setPIDCoefficients(Direction direction) {
        if (direction == Direction.UP) {
            m_pid.setP(PID_UP[0], PID_UPSLOT);
            m_pid.setI(PID_UP[1], PID_UPSLOT);
            m_pid.setD(PID_UP[2], PID_UPSLOT);
        }
        else {
            m_pid.setP(PID_DOWN[0], PID_DOWNSLOT);
            m_pid.setI(PID_DOWN[1], PID_DOWNSLOT);
            m_pid.setD(PID_DOWN[2], PID_DOWNSLOT);
        }
    }

    public void setMotorOpenLoop(double percent) {
        motor.set(percent);
    }

    public void setClosedLoop(Direction whereDirection) {
        direction = whereDirection;
        lastPosition = getPosition();
        if (direction == Direction.DOWN) { //If Direction is down
            if(limitBot.get() == false){ //If it is not touching the limit switch
                desiredPosition = getPosition() - kIncrement;
                moveToPosition(desiredPosition, direction);
            }
        }
        else { //If Direction is up
            if(getPosition() < kLimitTick){ //If it is below the tick limit
                desiredPosition = getPosition() + kIncrement;
                moveToPosition(desiredPosition, direction);
            }
        }

    }

    public void moveSetpoint(int setPoint){
        if (getPosition() > setPoint) { //If current position is higher than the setpoint
            direction = Direction.DOWN;
            moveToPosition(setPoint, direction);
        }
        else if (getPosition() < setPoint) { //If setpoint is higher than current position
            direction = Direction.UP;
            moveToPosition(setPoint, direction);
        }
    }

    public void moveToPosition(double position, Direction direction) {
        m_pid.setReference(position, ControlType.kPosition, (direction == Direction.UP) ? PID_UPSLOT : PID_DOWNSLOT);
    }

    public double getDesiredPosition() {
      return desiredPosition;
    }
    
    public double getError(){
        return getDesiredPosition() - getPosition();
    }

    public double getPosition() {
      return m_encoder.getPosition();
    }

    public double getLastPosition() {
        return lastPosition;
    }
    
    public void resetEncoder() {
        m_encoder.setPosition(0);
    }

    @Override
    protected void initDefaultCommand() {

    }
}