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
import frc.subsystems.Lift.LiftDirection;
import frc.commands.Autonomous;
import frc.commands.FollowLine;
import frc.commands.HatchReleaseOrHold;
import frc.commands.LEDcontrol;
import frc.commands.LEDcontrol.LEDmode;
import frc.commands.LEDcontrol.LEDcolor;
import frc.commands.LiftChinUpIntake;
import frc.commands.ShiftGear;
import frc.commands.CargoIntake;
import frc.commands.CargoShoot;
import frc.commands.CargoStop;
import frc.commands.ArmUpOrDown;
import frc.commands.ArmUpOrDown.armStatus;
import frc.commands.HatchReleaseOrHold.releaseOrHold;
import frc.commands.LiftChinUpIntake.IntakeStatus;
import frc.commands.LiftChinUpPullOpenLoop;
import frc.commands.LiftLegsUpOrDown;
import frc.commands.LiftUpOrDown;

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
    final JoystickButton jsButnClimb                 = new JoystickButton(m_joystick, 11);
    final JoystickButton jsButnRecord                = new JoystickButton(m_joystick, 10);
    final JoystickButton jsButnCalibrate             = new JoystickButton(m_joystick, 9);
    final JoystickButton jsButnShifter               = new JoystickButton(m_joystick, 8);
    final JoystickButton jsButnChinUpRetract         = new JoystickButton(m_joystick, 7);
    final JoystickButton jsButnChinUpForward         = new JoystickButton(m_joystick, 6);
    final JoystickButton jsButnChinUpOut             = new JoystickButton(m_joystick, 5);
    final JoystickButton jsButnChinUpIn              = new JoystickButton(m_joystick, 4);
    final JoystickButton jsButnRetractableLegsUp     = new JoystickButton(m_joystick, 3);
    final JoystickButton jsButnRetractableLegsDown   = new JoystickButton(m_joystick, 2);
    final JoystickButton jsButnFollowLine            = new JoystickButton(m_joystick, 1);
    final JoystickButton gpButnLEDOff                = new JoystickButton(m_gamepad, 10); //Gamepad Start button
    final JoystickButton gpButnLEDCargo              = new JoystickButton(m_gamepad, 8);  //Gamepad RT button
    final JoystickButton gpButnLEDHatch              = new JoystickButton(m_gamepad, 7);  //Gamepad LT button
    final JoystickButton gpButnArmRetract            = new JoystickButton(m_gamepad, 6);  //Gamepad RB button
    final JoystickButton gpButnArmForward            = new JoystickButton(m_gamepad, 5);  //Gamepad LB button
    final JoystickButton gpButnCargoOut              = new JoystickButton(m_gamepad, 4);  //Gamepad Y button
    final JoystickButton gpButnCargoIn               = new JoystickButton(m_gamepad, 3);  //Gamepad B button
    final JoystickButton gpButnPushHatch             = new JoystickButton(m_gamepad, 2);  //Gamepad A button
    final JoystickButton gpButnHoldHatch             = new JoystickButton(m_gamepad, 1);  //Gamepad X button


    /* Connect the buttons to commands */
    
    //JOYSTICK
    jsButnClimb.whenPressed(new LiftUpOrDown(LiftDirection.UP));
    jsButnShifter.whenPressed(new ShiftGear(GearShiftState.HI));
    jsButnShifter.whenReleased(new ShiftGear(GearShiftState.LO));
    jsButnChinUpRetract.whenPressed(new LiftChinUpPullOpenLoop(0.3));
    jsButnChinUpForward.whenPressed(new LiftChinUpPullOpenLoop(0.3));
    jsButnChinUpOut.whileHeld(new LiftChinUpIntake(IntakeStatus.INTAKE));
    jsButnChinUpOut.whenReleased(new LiftChinUpIntake(IntakeStatus.OFF));
    jsButnChinUpIn.whileHeld(new LiftChinUpIntake(IntakeStatus.OUTTAKE));
    jsButnChinUpIn.whenReleased(new LiftChinUpIntake(IntakeStatus.OFF));
    jsButnRetractableLegsUp.whenPressed(new LiftLegsUpOrDown(LiftDirection.UP));
    jsButnRetractableLegsDown.whenPressed(new LiftLegsUpOrDown(LiftDirection.DN));
    jsButnFollowLine.whenPressed(new FollowLine(true));
    jsButnFollowLine.whenReleased(new FollowLine(false));


    //GAMEPAD
    gpButnLEDOff.whenPressed(new LEDcontrol(LEDmode.OFF,LEDcolor.RED));
    gpButnLEDCargo.whenPressed(new LEDcontrol(LEDmode.SLOW,LEDcolor.RED));
    gpButnLEDHatch.whenPressed(new LEDcontrol(LEDmode.SLOW,LEDcolor.BLUE));
    gpButnCargoIn.whileHeld(new CargoIntake());
    gpButnCargoIn.whenReleased(new CargoStop());
    gpButnCargoOut.whileHeld(new CargoShoot());
    gpButnCargoOut.whenReleased(new CargoStop());
    gpButnArmRetract.whileHeld(new ArmUpOrDown(armStatus.BACKWARD));
    gpButnArmRetract.whenReleased(new ArmUpOrDown(armStatus.OFF));
    gpButnArmForward.whileHeld(new ArmUpOrDown(armStatus.FORWARD));
    gpButnArmForward.whenReleased(new ArmUpOrDown(armStatus.OFF));
    gpButnPushHatch.whenPressed(new HatchReleaseOrHold(releaseOrHold.release));
    gpButnHoldHatch.whenPressed(new HatchReleaseOrHold(releaseOrHold.hold));
  }

  public Joystick getJoystick() {
    return m_joystick;
  }

  public Joystick getGamepad() {
    return m_gamepad;
  }
}
