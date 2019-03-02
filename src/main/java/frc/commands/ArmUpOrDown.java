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

/* Arm is moved in open loop and is held in place by a closed loop */
public class ArmUpOrDown extends Command {
    public static enum armStatus {FORWARD, BACKWARD, OFF};
    private static final Arm arm = Robot.m_arm;
    public armStatus state;
    boolean isFinished;
    
    public ArmUpOrDown(armStatus state) {
        requires(arm);
        this.state = state;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        isFinished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        if (state == armStatus.OFF) {
            arm.keepPosition();
            // arm.stopMotor();
            isFinished = true;
        } else if (state == armStatus.FORWARD) {
            arm.moveUp();
        } else if (state == armStatus.BACKWARD) {
            arm.moveDown();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        // if (state == armStatus.OFF) {
        return arm.onTarget(); // closed loop
        // } else {
        //     return true; // open loop
        // }
        // return true;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        System.out.println("Arm ending...");
        // arm.stopMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    }
}
