/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.subsystems.Lift;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.utils.Constants;


public class ChinUpIntake extends InstantCommand {
    public static enum IntakeStatus {INTAKE, OUTTAKE, OFF};
    private static final Lift ChinUpIntake = Robot.m_lift;
    public IntakeStatus state;
    boolean limitValue;
    int status;
    
public ChinUpIntake(IntakeStatus state) {
    requires(ChinUpIntake);
    this.state = state;
}

// Called just before this Command runs the first time
@Override
protected void initialize() {
    if (state == IntakeStatus.INTAKE) {
        ChinUpIntake.wheelIntake();
        System.out.println("CHINUP INTAKING");
    }
    else if (state == IntakeStatus.OUTTAKE) {
        ChinUpIntake.wheelOuttake();
        System.out.println("CHINUP OUTTAKING");
    }
    else {
        ChinUpIntake.wheelStop();
        System.out.println("CHINUP STOPPING");
        }
    }
}