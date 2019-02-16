/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.utils.Constants;
import frc.subsystems.Arm;

public class ChinUpIntake extends InstantCommand {
    private static final Arm ARM = ;
    boolean limitValue;
    int status;
    JoystickButton(m_joystick, 4)
    public ChinUpIntake() {
      requires(ARM);
    }
  
    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        if (ARM.buttonGet() == true){
            cargo.wheelStop();
        }
        
        else {
        cargo.wheelIntake();
        System.out.println("INTAKING");
        }
    }   



     }
    }












}