// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.commands;

// import edu.wpi.first.wpilibj.command.Command;
// import frc.robot.Robot;
// import frc.subsystems.GamePieces;

// public class HatchReleaseOrHold extends Command {
//   /**
//    *
//    */

//   private static final GamePieces hatch = Robot.m_gamePieces;
//   public static enum releaseOrHold { release, hold };
//   private releaseOrHold relHold;
//   private int counter;
//   private static final int counterMax = 25;

//   public HatchReleaseOrHold(releaseOrHold relHold) {
//     // Use requires() here to declare subsystem dependencies
//     requires(hatch);
//     this.relHold = relHold;
//   }

//   // Called just before this Command runs the first time
//   @Override
//   protected void initialize() {
//     counter = 0;
//   }

//   // Called repeatedly when this Command is scheduled to run
//   @Override
//   protected void execute() {
//     if (relHold == releaseOrHold.hold) {
//       if (counter==0) {
//         hatch.fingerHold();
//      } else if (counter == counterMax) { // 25*20ms = 0.5s
//         hatch.pistonReset();
//       }
//       hatch.pistonPush();
//     } else {
//       if (counter==0) {
//          hatch.fingerRelease();
//       } else if (counter == counterMax) {
//          hatch.pistonPush();
//       }
//     }
//     counter=(counter+1)%(counterMax+1);
//   }

//   // Make this return true when this Command no longer needs to run execute()
//   @Override
//   protected boolean isFinished() {
//     return counter==counterMax;
//   }

//   // Called once after isFinished returns true
//   @Override
//   protected void end() {
//   }

//   // Called when another command which requires one or more of the same
//   // subsystems is scheduled to run
//   @Override
//   protected void interrupted() {
//   }
// }
