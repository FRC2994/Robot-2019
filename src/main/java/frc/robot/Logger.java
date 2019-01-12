/**
 * 
 */
package frc.robot;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * @author ASH
 *
 */
public class Logger {
    File logFile;
    File recFile;
    static String recLine;
    
    Logger() {
 	   File baseDrive = new File( "/home/lvuser");
 	   File logDir = new File(baseDrive, "logs");
 	   if (!logDir.exists()) {
 	       logDir.mkdirs();
 	   }
 	   
 	   int number = 0;
 	   logFile = new File(logDir, String.format("logger_%04d.txt", number));
 	   recFile = new File(logDir, String.format("recorder_%04d.csv", number));
 	   recLine = ""; // start a new line
    }
    
	public void println(String x) {
	   System.out.println(x);
	   try {
		   FileWriter logFileFw = new FileWriter(logFile, true);
		   logFileFw.write(x+'\n'); 
		   logFileFw.close();
	   } catch (IOException e) {
		   e.printStackTrace();	   
	   }
    }
	
	public void addRecord() {
	   try {
		   FileWriter recFileFw = new FileWriter(recFile, true);
		   recFileFw.write(recLine+'\n');
		   recFileFw.close();
		   recLine = ""; // start a new line
	   } catch (IOException e) {
		   e.printStackTrace();	   
	   }
   }

	public static void appendRecord(String x) {
		recLine += x;  // append string x to string line recLine
	}
	
}

