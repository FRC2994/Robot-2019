/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import java.io.File;
import frc.robot.Robot;
import frc.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.command.Command;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Trajectory.Segment;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.followers.EncoderFollower;
import jaci.pathfinder.modifiers.TankModifier;
import jaci.pathfinder.PathfinderFRC;

import edu.wpi.first.wpilibj.Notifier;
import edu.wpi.first.wpilibj.SpeedController;
import java.io.IOException;

public class TestPath extends Command {
  DriveTrain drivetrain = Robot.m_drivetrain;

  private EncoderFollower left;
  private EncoderFollower right;

  private int ticksPerRev = 3800;
  private double wheelDiameter = 6.15;
  private double velocity = 15;
  private double wheelbaseWidth = 2.25;

  File leftTraj = new File("/home/lvuser/deploy/paths/Unnamed.right.pf1.csv");
  File rightTraj = new File("/home/lvuser/deploy/paths/Unnamed.right.pf1.csv");

  Trajectory trajectory;
  Trajectory trajL;
  Trajectory trajR;

  private static int mp_direction = 1;

  public TestPath() {
    requires(drivetrain);
    try{
      trajL = Pathfinder.readFromCSV(leftTraj);
      trajR = Pathfinder.readFromCSV(rightTraj);
      this.left = new EncoderFollower(trajL);
      this.right = new EncoderFollower(trajR);
    } catch (IOException e) {
      e.printStackTrace();	   
    }

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    drivetrain.resetEncoders();
    left.configureEncoder((int)drivetrain.getLeftEncoderValue(),ticksPerRev, 5.9);
    left.configurePIDVA(0.01, 0, 0, 1 / velocity, 0);
    right.configureEncoder((int)drivetrain.getRightEncoderValue(), ticksPerRev, 5.9);
    right.configurePIDVA(0.01, 0, 0, 1 / velocity, 0);

    TankModifier modifier = new TankModifier(trajectory).modify(wheelbaseWidth);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double gyroError = drivetrain.getHeading() - Math.toDegrees(left.getHeading());
    double correction = mp_direction * 0.03 * Pathfinder.boundHalfDegrees(gyroError);
    int leftIn = drivetrain.getLeftEncoderValue();
    int rightIn = drivetrain.getRightEncoderValue();

    drivetrain.tankDrive(leftIn + correction, -1 * (rightIn - correction));
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return left.isFinished(); // left/right doesn't matter, they have the same number of steps
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
