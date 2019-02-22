/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Robot;
import frc.subsystems.GamePieces;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.utils.Constants;

public class CargoIntake extends InstantCommand {
  private static final GamePieces cargo = Robot.m_gamePieces;
  boolean limitValue;
  int status;
  
  public CargoIntake() {
    requires(cargo);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // if (cargo.buttonGet() == true){
    //   cargo.wheelStop();
    // }
    // else {
      cargo.wheelIntake();
      // System.out.println("INTAKING");
  //  }
  }
}
