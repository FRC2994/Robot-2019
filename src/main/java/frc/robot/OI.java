/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.subsystems.DriveTrain.GearShiftState;
import frc.commands.Autonomous;
import frc.commands.ShiftGear;
import frc.robot.Gamepad;
// import frc.robot.commands.CloseClaw;
// import frc.robot.commands.OpenClaw;
// import frc.robot.commands.Pickup;
// import frc.robot.commands.Place;
// import frc.robot.commands.PrepareToPickup;
// import frc.robot.commands.SetElevatorSetpoint;
// import frc.robot.commands.SetWristSetpoint;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  private final Joystick m_joystick = new Joystick(0);
	private final Gamepad m_controlGamepad = new Gamepad(0);

/**
   * Construct the OI and all of the buttons on it.
   */
  public OI() {
    // Put Some buttons on the SmartDashboard
    // SmartDashboard.putData("Elevator Bottom", new SetElevatorSetpoint(0));
    // SmartDashboard.putData("Elevator Platform", new SetElevatorSetpoint(0.2));
    // SmartDashboard.putData("Elevator Top", new SetElevatorSetpoint(0.3));

    // SmartDashboard.putData("Wrist Horizontal", new SetWristSetpoint(0));
    // SmartDashboard.putData("Raise Wrist", new SetWristSetpoint(-45));

    // SmartDashboard.putData("Open Claw", new OpenClaw());
    // SmartDashboard.putData("Close Claw", new CloseClaw());

    SmartDashboard.putData("Deliver Soda", new Autonomous());

    // Create some buttons
    // final JoystickButton dpadUp = new JoystickButton(m_joystick, 5);
    // final JoystickButton dpadRight = new JoystickButton(m_joystick, 6);
    // final JoystickButton dpadDown = new JoystickButton(m_joystick, 7);
    // final JoystickButton dpadLeft = new JoystickButton(m_joystick, 8);
    final JoystickButton l2 = new JoystickButton(m_joystick, 9);
    // final JoystickButton r2 = new JoystickButton(m_joystick, 10);
    // final JoystickButton l1 = new JoystickButton(m_joystick, 11);
    // final JoystickButton r1 = new JoystickButton(m_joystick, 12);
    final JoystickButton joystickCalibrate = new JoystickButton(m_joystick, 11);
    final JoystickButton joystickRecord = new JoystickButton(m_joystick, 4);
    final JoystickButton joystickShifter = new JoystickButton(m_joystick, 1);
  //    final JoystickButton joystickInverse = new JoystickButton(m_joystick, 2);
  //		public static final int JOYSTICK_PICKUP_IN = 6;
  //		public static final int JOYSTICK_PICKUP_OUT = 7;
  //		public static final int JOYSTICK_ELEVATOR_DOWN = 2;
  //		public static final int JOYSTICK_ELEVATOR_UP = 3;
  
    // Connect the buttons to commands
    joystickShifter.whenPressed(new ShiftGear(GearShiftState.HI));
    joystickShifter.whenReleased(new ShiftGear(GearShiftState.LO));
    // dpadUp.whenPressed(new SetElevatorSetpoint(0.2));
    // dpadDown.whenPressed(new SetElevatorSetpoint(-0.2));
    // dpadRight.whenPressed(new CloseClaw());
    // dpadLeft.whenPressed(new OpenClaw());

    // r1.whenPressed(new PrepareToPickup());
    // r2.whenPressed(new Pickup());
    // l1.whenPressed(new Place());
    l2.whenPressed(new Autonomous());
  }

  public Joystick getJoystick() {
    return m_joystick;
  }

  public Gamepad getGamepad() {
    return m_controlGamepad;
  }
}
