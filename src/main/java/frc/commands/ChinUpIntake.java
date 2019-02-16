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
import frc.subsystems.Lift.LiftDirection;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.utils.Constants;


public class ChinUpIntake extends InstantCommand {
    private static final Lift ChinUpIntake = Robot.m_lift;
    boolean limitValue;
    int status;
    
    private LiftDirection direction;
    public ChinUpIntake(LiftDirection direction) {
      this.direction = direction;
    }
    
// Called just before this Command runs the first time

    @Override
    protected void initialize() {
        if (direction == LiftDirection.UP){
            ChinUpIntake.wheelIntake();
            System.out.println("CHINUP INTAKING");
        }
        
        else {
        ChinUpIntake.wheelStop();
        }
    
        if (direction == LiftDirection.DN){
            ChinUpIntake.wheelOuttake();
            System.out.println("CHINUP OUTTAKING");
        }

        else {
        ChinUpIntake.wheelStop();
        }
    }     
}