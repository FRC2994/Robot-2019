/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.cscore.VideoSink;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.networktables.*;

public class Vision extends Subsystem {
    UsbCamera armCam;
    UsbCamera floorCam;
    VideoSink server;
    public enum selectedCamera {ARM,FLOOR};
    
    public Vision() {
      armCam = CameraServer.getInstance().startAutomaticCapture("armCam", 0);
      floorCam = CameraServer.getInstance().startAutomaticCapture("floorCam", 1);
      server = CameraServer.getInstance().getServer();
      armCam.setResolution(240, 180);
    }

    /*
    Code from 6333
    */

    public void switchCamera(selectedCamera camera){
        if (camera == selectedCamera.ARM){
            NetworkTableInstance.getDefault().getTable("").getEntry("CameraSelection").setString(armCam.getName());
            // server.setSource(armCam);
        }
        else if (camera == selectedCamera.FLOOR){
            NetworkTableInstance.getDefault().getTable("").getEntry("CameraSelection").setString(floorCam.getName());
            // server.setSource(floorCam);
        }
    }

  @Override
  protected void initDefaultCommand() {

  }
}