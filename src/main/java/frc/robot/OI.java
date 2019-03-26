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
//import frc.subsystems.DriveTrain.driveStatus;
import frc.commands.Autonomous;
import frc.commands.FollowLine;
import frc.commands.FollowLine.lineGo;
import frc.commands.HatchReleaseOrHold;
import frc.commands.LEDcontrol;
import frc.commands.LEDcontrol.LEDstate;
import frc.commands.LiftChinUpClosedLoop;
import frc.commands.LiftChinUpClosedLoop.chinUpDirection;
// import frc.commands.LEDcontrol.LEDcolor;
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
// import frc.commands.LiftUpOrDown;
// import frc.commands.ZeroArm;
import frc.commands.HatchFinger;
import frc.commands.HatchPiston;
import frc.commands.HatchPiston.state;
import frc.commands.CameraSwitch;
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

    //SmartDashboard.putData("Deliver Soda", new Autonomous());

    // Create some buttons
    //Joystick
    // final JoystickButton jsButnRecord                = new JoystickButton(m_joystick, 11);
    // final JoystickButton jsButnCalibrate             = new JoystickButton(m_joystick, 10);
    final JoystickButton jsButnShifter               = new JoystickButton(m_joystick, 12);
    // final JoystickButton jsButnClimb                 = new JoystickButton(m_joystick, 11);
    // final JoystickButton jsButnSwitchCamera          = new JoystickButton(m_joystick, 11);
    final JoystickButton jsButnChinUpRetract         = new JoystickButton(m_joystick, 9);    
    final JoystickButton jsButnChinUpForward         = new JoystickButton(m_joystick, 10);    
    final JoystickButton jsButnRetractableLegsDown   = new JoystickButton(m_joystick, 7);    
    final JoystickButton jsButnRetractableLegsUp     = new JoystickButton(m_joystick, 8);
    final JoystickButton jsButnChinUpOut             = new JoystickButton(m_joystick, 4);
    final JoystickButton jsButnChinUpIn              = new JoystickButton(m_joystick, 3);
    final JoystickButton jsButnReverse               = new JoystickButton(m_joystick, 2);    
    final JoystickButton jsButnFollowLine            = new JoystickButton(m_joystick, 1);


    //GamePad
    final JoystickButton gpButnLEDOff                = new JoystickButton(m_gamepad, 7); //Gamepad Back button
    final JoystickButton gpButnLED                   = new JoystickButton(m_gamepad, 8);  //Gamepad Start button
    // final JoystickButton gpButnArmZero               = new JoystickButton(m_gamepad, 12);
    final JoystickButton gpButnArmRetract            = new JoystickButton(m_gamepad, 6);  //Gamepad RB button
    final JoystickButton gpButnArmForward            = new JoystickButton(m_gamepad, 5);  //Gamepad LB button
    final JoystickButton gpButnCargoOut              = new JoystickButton(m_gamepad, 4);  //Gamepad Y button
    final JoystickButton gpButnCargoIn               = new JoystickButton(m_gamepad, 2);  //Gamepad B button
    final JoystickButton gpButnHatchPiston           = new JoystickButton(m_gamepad, 1);  //Gamepad A button
    final JoystickButton gpButnHatchFinger           = new JoystickButton(m_gamepad, 3);  //Gamepad X button


    /* Connect the buttons to commands */
    
    //JOYSTICK
    //jsButnClimb.whenPressed(new LiftUpOrDown(LiftDirection.UP));
    // jsButnSwitchCamera.whenPressed(new CameraSwitch());
    jsButnShifter.whenPressed(new ShiftGear(GearShiftState.HI));
    jsButnShifter.whenReleased(new ShiftGear(GearShiftState.LO));
    jsButnChinUpRetract.whileHeld(new LiftChinUpClosedLoop(chinUpDirection.UP));
    jsButnChinUpForward.whileHeld(new LiftChinUpClosedLoop(chinUpDirection.DOWN));
    jsButnChinUpOut.whileHeld(new LiftChinUpIntake(IntakeStatus.OUTTAKE));
    jsButnChinUpOut.whenReleased(new LiftChinUpIntake(IntakeStatus.OFF));
    jsButnChinUpIn.whileHeld(new LiftChinUpIntake(IntakeStatus.INTAKE));
    jsButnChinUpIn.whenReleased(new LiftChinUpIntake(IntakeStatus.OFF));
    jsButnRetractableLegsUp.whenPressed(new LiftLegsUpOrDown(LiftDirection.UP));
    jsButnRetractableLegsDown.whenPressed(new LiftLegsUpOrDown(LiftDirection.DN));
    jsButnFollowLine.whileHeld(new FollowLine(lineGo.GO));
    jsButnFollowLine.whenReleased(new FollowLine(lineGo.STOP));

    //GAMEPAD
    gpButnLEDOff.whenPressed(new LEDcontrol(LEDstate.OFF));
    gpButnLED.whenPressed(new LEDcontrol(LEDstate.ON));
    // gpButnArmZero.whenPressed(new ZeroArm());
    gpButnCargoIn.whileHeld(new CargoIntake());
    gpButnCargoIn.whenReleased(new CargoStop());
    gpButnCargoOut.whileHeld(new CargoShoot());
    gpButnCargoOut.whenReleased(new CargoStop());
    gpButnArmRetract.whenPressed(new ArmUpOrDown(armStatus.BACKWARD));
    // gpButnArmRetract.whenReleased(new ArmUpOrDown(armStatus.OFF));
    gpButnArmForward.whenPressed(new ArmUpOrDown(armStatus.FORWARD));
    // gpButnArmForward.whenReleased(new ArmUpOrDown(armStatus.OFF));
    gpButnHatchFinger.whenPressed(new HatchFinger());
    gpButnHatchPiston.whileHeld(new HatchPiston(state.PUSHED));
    gpButnHatchPiston.whenReleased(new HatchPiston(state.RESET));
  }

  public Joystick getJoystick() {
    return m_joystick;
  }

  public Joystick getGamepad() {
    return m_gamepad;
  }
}
