/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.AnalogInput;

import frc.subsystems.Logger;
import frc.utils.Constants;
import static frc.utils.Constants.*;

/**
 * Add your docs here.
 */
public class LineFollower extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

	private static LineFollower instance;

  public LineFollower() {
    instance = this;
  }

  DigitalInput rightColorSensor = new DigitalInput(Constants.DIO_RIGHT_COLOUR_SENSOR);  // TODO: use a constant
  DigitalInput leftColorSensor = new DigitalInput(Constants.DIO_LEFT_COLOUR_SENSOR);   // TODO: use a constant
 // String direction;  // TODO:Use an Enum state = rightState, leftState, bothState, noneState

  public static final double closeDistance = 5; // 5 inches
  private static boolean rightColorSensorValue;
  private static boolean leftColorSensorValue; 
  private static double rightDistance;
  private static double leftDistance;
  private static State state;

  public enum State {
    rightState ('R'),
    leftState ('L'),
    centreState ('C'),
    noneState ('N'),
    finishedState ('F'),
    invalidState ('I')
    ;

    private final char stateCode;

    private State(char stateCode) {
        this.stateCode = stateCode;
    }
  
  }

  public static LineFollower getInstance() {
      return instance;
  }

  public State getState() {
      rightColorSensorValue = rightColorSensor.get();
      leftColorSensorValue = leftColorSensor.get(); 

      if(leftColorSensorValue == false && rightColorSensorValue == true) {
          //Only right sensor sees white so it should go left
          state = State.leftState;

      } else if(leftColorSensorValue == true && rightColorSensorValue == false) {
          //Only Left sensor sees white so it should go right
          state = State.rightState;

        } else if(leftColorSensorValue == true && rightColorSensorValue == true) {
          //Both sensors see the white line therefore should go straight
          state = State.centreState;

        } else {
          //None of sensors sees the white line therefore if should stop
          state = State.noneState;

      }

      return state;
  }

  @Override
	public void periodic() {
    Logger.appendRecord("\t"+state+"\t"+leftDistance+"\t"+rightDistance+"\t");
  }	

  @Override
  protected void initDefaultCommand() {
    Logger.appendRecord("lfS\tlfLD\tlfRD\t");
  }

}
