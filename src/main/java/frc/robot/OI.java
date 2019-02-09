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
    final JoystickButton jsButnRecord = new JoystickButton(m_joystick, 4);
    final JoystickButton jsButnShifter = new JoystickButton(m_joystick, 1);
    final JoystickButton gpButnCargoIn = new JoystickButton(m_gamepad, 4); //Gamepad Y button
    final JoystickButton gpButnCargoOut = new JoystickButton(m_gamepad, 3); //Gamepad B Button
  
    /* Connect the buttons to commands */
    //JOYSTICK
    jsButnShifter.whenPressed(new ShiftGear(GearShiftState.HI));
    jsButnShifter.whenReleased(new ShiftGear(GearShiftState.LO));

    //GAMEPAD
    gpButnCargoIn.whileHeld(new CargoIntake(cargoState.GO));
    gpButnCargoIn.whenReleased(new CargoIntake(cargoState.STOP));
    gpButnCargoOut.whileHeld(new CargoShoot(cargoState.GO));
    gpButnCargoOut.whenReleased(new CargoShoot(cargoState.STOP));
  }

  public Joystick getJoystick() {
    return m_joystick;
  }

  public Joystick getGamepad() {
    return m_gamepad;
  }
}
