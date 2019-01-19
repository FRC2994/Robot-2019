package frc.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.subsystems.DriveTrain;
import frc.robot.Robot;

public class DriveCurve extends Command{
   
    public DriveCurve(){
        requires(Robot.drivetrain);
        setTimeout(1);
    }

    private void requires(DriveTrain drivetrain) {
    }

    protected void initialize(){
        Robot.drivetrain.driveWithCurve(1, 30);
    }

    protected void execute(){
    }


    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end(){
        // Stop
    }

    protected void interupted(){
        end();
    }

}