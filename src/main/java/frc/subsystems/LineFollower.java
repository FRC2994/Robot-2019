/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.AnalogInput;
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
  AnalogInput rightUltrasonicSensor = new AnalogInput(Constants.AIO_RIGHT_ULTRASONIC_SENSOR);
  AnalogInput leftUltrasonicSensor = new AnalogInput(Constants.AIO_LEFT_ULTRASONIC_SENSOR);
  public static final double closeDistance = 5; // 5 inches
  
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
      boolean rightColorSensorValue = rightColorSensor.get();
      boolean leftColorSensorValue = leftColorSensor.get(); 
      //DISTANCES ARE IN INCHES
      double rightDistance = (rightUltrasonicSensor.getValue()*5)/25.4; //Gets the value of the sensor, turns the bits into mm, the turns it into inches
      double leftDistance = (leftUltrasonicSensor.getValue()*5)/25.4;

      State state;
      if (leftDistance <= closeDistance && rightDistance <= closeDistance){
        state = State.finishedState;   
      } else if(leftColorSensorValue == false && rightColorSensorValue == true) {
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
      /* switch(state){
          case rightState:    System.out.println("Move Right");
                              return 'R';
                              break;
          case leftState:     System.out.println("Move Left"); 
                              break;
          case centreState:   System.out.println("Move Straight");
                              break;
          default:            System.out.println("Invalid");
                              break;
      } */
  }

  @Override
	public void initTeleop() {}
  @Override
	public void tickTeleop() {}
	
  @Override
	public void initAutonomous() {}
  @Override
	public void tickAutonomous() {}
	
  @Override
	public void initTesting() {}
  @Override
	public void tickTesting() {}

}
