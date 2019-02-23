/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.subsystems.Arm;
import frc.utils.Constants;

public class ArmUpOrDown extends Command {
    public static enum armStatus {FORWARD, BACKWARD, OFF};
    private static final Arm arm = Robot.m_arm;
    public armStatus state;
    private boolean armFinished;
    private double position;
    
    public ArmUpOrDown(armStatus state) {
        requires(arm);
        this.state = state;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        if (state == armStatus.OFF) {
            armFinished = true;
            arm.stopMotor();
        } else {
            if(arm.LimitArmTop.get()) {
                arm.stopMotor();
            }
            else {
            armFinished = false;
            }
        }
        if (state == armStatus.FORWARD) {
            //arm.moveUp()
            position = -30;
            arm.setPosition(-30);

            // System.out.println("FORWARD");
        }
        else if (state == armStatus.BACKWARD) {
            //arm.moveDown();
            position = 30;
            arm.setPosition(30);
        }
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        double diff = Math.abs(arm.getRealPosition()-position);
        System.out.println("Arm isFinished " + armFinished + " diff " + diff);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        double diff = Math.abs(arm.getRealPosition()-position);
        if (diff == 1 ) {
            armFinished = true;
        }
        return armFinished;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        System.out.println("Arm ending...");
        arm.stopMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
