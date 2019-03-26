/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.subsystems.LiftIntake;

public class LiftChinUpIntake extends InstantCommand {
    public static enum IntakeStatus {INTAKE, OUTTAKE, OFF};
    private static final LiftIntake chinUpIntake = Robot.m_liftIntake;
    public IntakeStatus state;
    int status;

    public LiftChinUpIntake(IntakeStatus state) {
        requires(chinUpIntake);
        this.state = state;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        if(state == IntakeStatus.INTAKE) {
            chinUpIntake.intake();
        }
        else if(state == IntakeStatus.OUTTAKE){
            chinUpIntake.outtake();
        }
        else {
            chinUpIntake.stop();
        }
    }
}
