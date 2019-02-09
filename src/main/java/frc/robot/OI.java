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
import frc.subsystems.GamePieces.cargoState;
import frc.commands.Autonomous;
import frc.commands.FollowLine;
import frc.commands.ShiftGear;
import frc.commands.CargoIntake;
import frc.commands.CargoShoot;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  private final Joystick m_joystick = new Joystick(0);
  private final Joystick m_gamepad = new Joystick(1);

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
    final JoystickButton jsButnCalibrate = new JoystickButton(m_joystick, 11);
    final JoystickButton jsButnRecord = new JoystickButton(m_joystick, 10);
    final JoystickButton jsButnShifter = new JoystickButton(m_joystick, 8);
    final JoystickButton jsButnChinUpRetract = new JoystickButton(m_joystick, 7);
    final JoystickButton jsButnChinUpForward = new JoystickButton(m_joystick, 6);
    final JoystickButton jsButnChinUpOut = new JoystickButton(m_joystick, 5);
    final JoystickButton jsButnChinUpIn = new JoystickButton(m_joystick, 4);
    final JoystickButton jsButnRetractableLegsUp = new JoystickButton(m_joystick, 3);
    final JoystickButton jsButnRetractableLegsDown = new JoystickButton(m_joystick, 2);
    final JoystickButton jsButnFollowLine = new JoystickButton(m_joystick, 1);
    final JoystickButton gpButnHatchPiston = new JoystickButton(m_gamepad, 8);   //Gamepad RT button
    final JoystickButton gpButnArmRetract = new JoystickButton(m_gamepad, 6);    //Gamepad RB button
    final JoystickButton gpButnArmForward = new JoystickButton(m_gamepad, 5);    //Gamepad LB button
    final JoystickButton gpButnCargoOut = new JoystickButton(m_gamepad, 4);      //Gamepad Y button
    final JoystickButton gpButnCargoIn = new JoystickButton(m_gamepad, 3);       //Gamepad B Button
    final JoystickButton gpButnFingerRetract = new JoystickButton(m_gamepad, 2); //Gamepad A button
    final JoystickButton gpButnFingerHold = new JoystickButton(m_gamepad, 1);    //Gamepad X button


    /* Connect the buttons to commands */
    
    //JOYSTICK
    jsButnShifter.whenPressed(new ShiftGear(GearShiftState.HI));
    jsButnShifter.whenReleased(new ShiftGear(GearShiftState.LO));
    // jsButnChinUpRetract.whenPressed(new );
    // jsButnChinUpForward.whenPressed(new );
    // jsButnChinUpOut.whileHeld(new ?(?.GO));
    // jsButnChinUpOut.whenReleased(new ?(?.STOP));
    // jsButnChinUpIn.whileHeld(new ?(?.GO));
    // jsButnChinUpIn.whenReleased(new ?(?.STOP));
    // jsButnRetractableLegsUp.whenPressed(new );
    // jsButnRetractableLegsDown.whenPressed(new );
    jsButnFollowLine.whileHeld(new FollowLine());
 

    //GAMEPAD
    // gpButnHatchPiston.whenPressed(new );
    // gpButnArmRetract.whileHeld(new ?(?.GO));
    // gpButnArmRetract.whenReleased(new ?(?.STOP));
    // gpButnArmForward.whileHeld(new ?(?.GO));
    // gpButnArmForward.whenReleased(new ?(?.STOP));
    gpButnCargoIn.whileHeld(new CargoIntake(cargoState.GO));
    gpButnCargoIn.whenReleased(new CargoIntake(cargoState.STOP));
    gpButnCargoOut.whileHeld(new CargoShoot(cargoState.GO));
    gpButnCargoOut.whenReleased(new CargoShoot(cargoState.STOP));
    //gpButnFingerRetract.whenPressed(new );
    //gpButnFingerHold.whenPressed(new );
  }

  public Joystick getJoystick() {
    return m_joystick;
  }

  public Joystick getGamepad() {
    return m_gamepad;
  }
}
