package frc.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;

public class LineFollower extends Subsystems {
    DigitalInput rightColorSensor = new DigitalInput(1);
    DigitalInput leftColorSensor = new DigitalInput(2);
    boolean rightColorSensorValue = rightColorSensor.get();
    boolean leftColorSensorValue = leftColorSensor.get(); 
    public LineFollower()
    {
        if(rightColorSensorValue = true) {
            
        }
        if(leftColorSensorValue = true) {

        }

    }
    public static void start() {
        if(leftColorSensorValue = false)
    }
}