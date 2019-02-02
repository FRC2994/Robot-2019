package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.subsystems.DriveTrain;
import frc.robot.Robot;

public class DriveCurve extends Command{
   
    public DriveCurve(double speed, double turn, boolean isQuickTurn){
        requires(Robot.m_drivetrain);
        setTimeout(1);
        Robot.m_drivetrain.driveWithCurve(speed, turn, isQuickTurn);
    }

    protected void initialize(){
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