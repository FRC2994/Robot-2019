/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.subsystems.Vision;
import frc.subsystems.Vision.selectedCamera;
import frc.robot.Robot;
public class CameraSwitch extends InstantCommand {
  private final Vision vision = Robot.m_vision;
  private int cameraChoice = 0;

  public CameraSwitch() {
    super();
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    if(cameraChoice == 0) {
      vision.switchCamera(selectedCamera.ARM);
      cameraChoice = 1;
      System.out.println("SWITCHED TO ARM CAMERA");
    }
    else{
      vision.switchCamera(selectedCamera.FLOOR);
      cameraChoice = 2;
      System.out.println("SWITCH TO FLOOR CAMERA");
    }
  }

}
