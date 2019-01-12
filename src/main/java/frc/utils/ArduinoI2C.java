package frc.utils;

import edu.wpi.first.wpilibj.I2C;

public class ArduinoI2C {
		public void sendMessage(String X, int Port) {
			I2C Wire = new I2C(I2C.Port.kOnboard, Port);
			char[] CharArray = X.toCharArray(); //Turns the message into an array
			byte[] WriteData = new byte[CharArray.length]; //Turns the array into a byte
			for (int i = 0; i < CharArray.length; i++) {
				WriteData[i] = (byte) CharArray[i];
			}
		}
		public ArduinoI2C() {
			sendMessage("test", 4);
		}
}

