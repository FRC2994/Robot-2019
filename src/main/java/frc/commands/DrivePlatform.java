// package frc.commands;

// import edu.wpi.first.wpilibj.command.Command;
// import edu.wpi.first.wpilibj.command.CommandGroup;
// import frc.subsystems.DriveTrain;
// import frc.commands.DriveStaight;
// import frc.robot.Robot; 

// public class DrivePlatform extends CommandGroup
// {
//     public DrivePlatform() {
//       addSequential(new DriveStaight(DriveStaight));
//       addParallel(new LiftLegsUpOrDown());
//       addSequential(new DriveStaight(DrivePlatform));
//       addParallel(new LiftChinUpMoveToPosition());
//       addSequential(new DriveStaight(DriveStaight));
//     }

//     public DrivePlatform() {
//       requires(Robot.DriveTrain.DriveStaight);
//       setTimeout();
//     }
    
//     protected void initialize() {
//     }
    
//     protected void excute() {
//     }

//     protected boolean isFinished() {
//         return isTimedOut();
//     }

//     protected void interrupted() {

//     }
// }