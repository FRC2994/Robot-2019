package frc.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import frc.subsystems.DriveTrain;

public class LineFollower extends Subsystems {
    DigitalInput rightColorSensor = new DigitalInput(1);
    DigitalInput leftColorSensor = new DigitalInput(2);
    boolean rightColorSensorValue = rightColorSensor.get();
    boolean leftColorSensorValue = leftColorSensor.get(); 
    String direction;

    DriveTrain dT = new DriveTrain();
    
    public void startFollow() {
        if(leftColorSensorValue == false && rightColorSensorValue == true) {
            //Right sensor only sees white so it should go left
            direction = "R";
        }
        if(leftColorSensorValue == true && rightColorSensorValue == false) {
            //Left sensor only sees white so it should go right
            direction = "L";
        }
        if(leftColorSensorValue == true && rightColorSensorValue == true) {
            //Both sensors see the white line therefore should go straight
            direction = "C";
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
}