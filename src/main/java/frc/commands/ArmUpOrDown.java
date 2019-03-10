/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.subsystems.Arm;
import frc.robot.Robot;
public class ArmUpOrDown extends InstantCommand {
  private static final Arm arm = Robot.m_arm;
  public static enum armStatus {FORWARD, BACKWARD};
  private armStatus state;
  public ArmUpOrDown(armStatus state) {
    super();
    requires(arm);
    this.state = state;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    if (state==armStatus.FORWARD) {
      arm.armDown();
    }
    if (state==armStatus.BACKWARD){
      arm.armUp();
    }
  }

}
