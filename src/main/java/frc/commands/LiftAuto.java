/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import frc.subsystems.DriveTrain;
import frc.subsystems.Lift;
import frc.subsystems.Lift.LiftDirection;

import frc.robot.Robot;

import frc.commands.LiftLegsUpOrDown;
import frc.commands.LiftChinUpClosedLoop;
import frc.commands.LiftArmMoveDown;
import frc.commands.LiftChinUpIntake;
import frc.commands.LiftChinUpIntake.IntakeStatus;
import frc.commands.LiftChinUpClosedLoop.chinUpDirection;
import frc.commands.DriveTimed;

public class LiftAuto extends CommandGroup {
  private final Lift lift = Robot.m_lift;
  private final DriveTrain drive = Robot.m_drivetrain;
  public LiftAuto() {
    requires(lift);
    requires(drive);

    drive.setStopArcadeDrive(true);
    System.out.println("Starting to climb!");
    addSequential(new LiftArmMoveDown()); //Moves arm down until it reaches the bottom of the platform
    addParallel(new LiftLegsUpOrDown(LiftDirection.DN)); //Puts down the legs while arm is going down
    addSequential(new LiftChinUpIntake(IntakeStatus.INTAKE)); //Climb intake goes forward after legs go down
    addParallel(new LiftChinUpClosedLoop(chinUpDirection.DOWN));//Moves the arm a little bit down
    addParallel(new DriveTimed(0.5,5));//50% speed for 5 seconds
    addSequential(new LiftLegsUpOrDown(LiftDirection.UP)); //Puts legs up
    addSequential(new LiftChinUpIntake(IntakeStatus.OFF));
    addSequential(new DriveTimed(0.3, 2));//30% for 2 seconds
    System.out.println("Finished Climb!");
    drive.setStopArcadeDrive(false);

    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.


  }
}
