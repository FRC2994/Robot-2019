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

public class ArmUpOrDown extends Command {
    public static enum armStatus {FORWARD, BACKWARD, OFF};
    private static final Arm arm = Robot.m_arm;
    public static armStatus status;
    private boolean armFinished;

    public ArmUpOrDown(armStatus status) {
        // Use requires() here to declare subsystem dependencies
        requires(arm);
        this.status = status;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        if (status == armStatus.OFF) {
            armFinished = true;
        }
        else {
            armFinished = false;
        }
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        if (status == armStatus.FORWARD) {
            arm.moveUp();
        }
        else if (status == armStatus.BACKWARD) {
            arm.moveDown();
        }
        else {
            arm.stopMotor();
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return armFinished;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
        arm.stopMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
        arm.stopMotor();
    }
}
