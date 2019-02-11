/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.subsystems.Lift;
import frc.subsystems.Lift.LiftDirection;

public class LiftUpOrDown extends CommandGroup {
  public LiftUpOrDown(LiftDirection direction) {
    final int chinupOnPlatform  = 2000; 
    if (direction==LiftDirection.DN) {
      // Move robot ahaead to get space for chinup
      addSequential(new DriveStraight(2000));
      // Bring legs down and get chinup to rest on platform
      addSequential(new LiftLegsUpOrDown(LiftDirection.DN));
      addParallel(new LiftChinUpMoveToPosition(2000));
      // Move robot off platform; weight is on chinup and legs
      addSequential(new DriveStraight(5000));
      // Bring robot down
      addParallel(new LiftChinUpMoveToPosition(0));
      addSequential(new LiftLegsUpOrDown(LiftDirection.UP));
      // Stow chinup
      // TBD addSequential(new LiftChinupMoveToPosition(0));
    } else {
      // Bring chinup to the height of platform
      addSequential(new LiftChinUpMoveToPosition(chinupOnPlatform+500));
      // Move robot ahaead so chinup can rest on platform
      addSequential(new DriveStraight(2000));
      // Make chinup take some weight off wheels
      addSequential(new LiftChinUpMoveToPosition(chinupOnPlatform));
      // Extend legs and get chinup to rest on platform
      addSequential(new LiftLegsUpOrDown(LiftDirection.UP));
      addParallel(new LiftChinUpMoveToPosition(2000));
      // Move robot on platform;
      addSequential(new LiftChinUpPullOpenLoop(5000));
      // Stow chinup
      // TBD addSequential(new LiftChinupMoveToPosition(0));
    }
  }
}
