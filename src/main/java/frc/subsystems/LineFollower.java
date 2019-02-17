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
import edu.wpi.first.wpilibj.Ultrasonic;
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
  private static DigitalInput rightColorSensor;
  private static DigitalInput leftColorSensor;
  private static Ultrasonic   rightUltrasonic;
  private static Ultrasonic   leftUltrasonic;

  public LineFollower() {
    instance = this;
    rightColorSensor = new DigitalInput(Constants.DIO_RIGHT_COLOUR_SENSOR);
    leftColorSensor = new DigitalInput(Constants.DIO_LEFT_COLOUR_SENSOR);
    rightUltrasonic = new Ultrasonic(Constants.DIO_RUS_ECHO, Constants.DIO_RUS_TRIG);
    leftUltrasonic = new Ultrasonic(Constants.DIO_LUS_ECHO, Constants.DIO_LUS_TRIG);

    rightUltrasonic.setAutomaticMode(true);
    leftUltrasonic.setAutomaticMode(true);
  }
  
 // String direction;

  public static final double closeDistance = 5.0; // 5 inches
  public static final double finishDistance = 2.0; // 2 Inches
  private static boolean rightColorSensorValue;
  private static boolean leftColorSensorValue; 
  private static double rightDistance;
  private static double leftDistance;
  private static State state;

  public enum State {
    rightState,
    leftState,
    centreState,
    noneState,
    finishedState,
    almostState,
    invalidState
  
  }

  public static LineFollower getInstance() {
      return instance;
  }

  public State getState() {
      rightColorSensorValue = rightColorSensor.get();
      leftColorSensorValue = leftColorSensor.get(); 
      rightDistance = rightUltrasonic.getRangeInches();
      leftDistance = leftUltrasonic.getRangeInches();

      // if (leftColorSensorValue == false || rightColorSensorValue == false && rightDistance <= 2 && leftDistance <= 2) {
      //     //It is finished
      //     state = State.finishedState;
      // } else if (leftColorSensorValue == false || rightColorSensorValue == false && rightDistance <= 5 && leftDistance <=5){
      //     //Both color sensors sense white and the ultrasonic is within 5 inches
      //     state = State.almostState;
      // } 
      if(leftColorSensorValue == true && rightColorSensorValue == false) {
          //Only right sensor sees white so it should go left
          state = State.leftState;

      } else if(leftColorSensorValue == false && rightColorSensorValue == true) {
          //Only Left sensor sees white so it should go right
          state = State.rightState;

      } else if(leftColorSensorValue == false && rightColorSensorValue == false) {
          //Both sensors see the white line therefore should go straight
          state = State.centreState;

      } else {
          //None of sensors sees the white line therefore if should stop
          state = State.noneState;

      }

      return state;
    }

public void debugColor() {
  // System.out.println(leftColorSensor.get() + " "+ rightColorSensor.get());
  System.out.println(getState());
}
public void debugUS() {
  System.out.println("Right Ultrasonic: " + rightUltrasonic.getRangeInches());
  System.out.println("Left Ultrasonic: " + leftUltrasonic.getRangeInches());
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
