/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;

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

  DigitalInput rightColorSensor = new DigitalInput(27);  // TODO: use a constant
  DigitalInput leftColorSensor = new DigitalInput(26);   // TODO: use a constant
  String direction;  // TODO:Use an Enum state = rightState, leftState, bothState, noneState

  public static LineFollower getInstance() {
      return instance;
  }

  public void startFollow() {
      boolean rightColorSensorValue = rightColorSensor.get();
      boolean leftColorSensorValue = leftColorSensor.get(); 
      
      if(leftColorSensorValue == false && rightColorSensorValue == true) {
          //Only right sensor sees white so it should go left
          direction = "R";
      } else if(leftColorSensorValue == true && rightColorSensorValue == false) {
          //Only Left sensor sees white so it should go right
          direction = "L";
        } else if(leftColorSensorValue == true && rightColorSensorValue == true) {
          //Both sensors see the white line therefore should go straight
          direction = "C";
        } else {
          //None of sensors sees the white line therefore if should stop
          direction = "S";
      }
      switch(direction){
          case "L":   System.out.println("Move Right");
                      break;
          case "R":   System.out.println("Move Left"); 
                      break;
          case "C":   System.out.println("Move Straight");
                      break;
          default:    System.out.println("Invalid");
                      break;
      }
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
