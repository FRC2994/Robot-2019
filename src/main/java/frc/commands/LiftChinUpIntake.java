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

public class LiftChinUpIntake extends InstantCommand {
    public static enum IntakeStatus {INTAKE, OUTTAKE, OFF};
    private static final Lift LiftLiftChinUpIntake = Robot.m_lift;
    public IntakeStatus state;
    public IntakeStatus position;
    boolean limitValue;
    int status;
    private final double defaultSpeed = 1;
    private boolean openLoopMode = true;

    public LiftChinUpIntake(IntakeStatus state) {
        requires(LiftLiftChinUpIntake);
        this.state = state;
        openLoopMode = true;
    }

    public LiftChinUpIntake(double position) {
        requires(LiftLiftChinUpIntake);
        openLoopMode = false;
    }

    public LiftChinUpIntake() {
        requires(LiftLiftChinUpIntake);
        openLoopMode = false;
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        if (openLoopMode) {
            if (state == IntakeStatus.INTAKE) {
                LiftLiftChinUpIntake.chinUpMoveOpenLoop(defaultSpeed);
                System.out.println("CHINUP INTAKING");
            }
            else if (state == IntakeStatus.OUTTAKE) {
                LiftLiftChinUpIntake.chinUpMoveOpenLoop(-defaultSpeed);
                System.out.println("CHINUP OUTTAKING");
            }
            else {
                LiftLiftChinUpIntake.chinUpMoveOpenLoop(0);
                System.out.println("CHINUP STOPPING");
            }
        } else {
            if (position == IntakeStatus.INTAKE) {
                LiftLiftChinUpIntake.chinUpMoveOpenLoop(defaultSpeed);
                System.out.println("CHINUP INTAKING");
            } else if (position == IntakeStatus.OUTTAKE) {
                LiftLiftChinUpIntake.chinUpMoveOpenLoop(-defaultSpeed);
                System.out.println("CHINUP OUTTAKING");
            } else {
                LiftLiftChinUpIntake.chinUpMoveOpenLoop(0);
                System.out.println("CHINUP STOPPING");
            }
        }
    }
}
