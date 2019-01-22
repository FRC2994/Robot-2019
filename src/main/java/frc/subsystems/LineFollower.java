/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;

import static frc.utils.Constants.DIO_RIGHT_COLOUR_SENSOR;
import static frc.utils.Constants.DIO_LEFT_COLOUR_SENSOR;
import static frc.utils.Constants.AIO_LEFT_ULTRASONIC_SENSOR;
import static frc.utils.Constants.AIO_RIGHT_ULTRASONIC_SENSOR;
import static frc.utils.Constants.getConstantAsInt;

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

  DigitalInput rightColorSensor = new DigitalInput(getConstantAsInt(DIO_RIGHT_COLOUR_SENSOR));  // TODO: use a constant
  DigitalInput leftColorSensor = new DigitalInput(getConstantAsInt(DIO_LEFT_COLOUR_SENSOR));   // TODO: use a constant
 // String direction;  // TODO:Use an Enum state = rightState, leftState, bothState, noneState
  
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
      State state;
      
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
