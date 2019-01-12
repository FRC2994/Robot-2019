package frc.autonomous.commands;

import frc.autonomous.AutoCommand;
import edu.wpi.first.wpilibj.DriverStation;

public class GetMatchInfo implements AutoCommand {
 
	private static boolean isSwitchOnLeftSide;
	private static boolean isScaleOnLeftSide;

    public GetMatchInfo() {
        int retries = 100;
        String gameData;
        do {
            gameData = DriverStation.getInstance().getGameSpecificMessage();
            try {
                Thread.sleep(100);
            } catch (InterruptedException ie) {
                // Just ignore the interrupted exception
            }
        } while (gameData.length() < 2 && --retries > 0);
        if (gameData.length()>0) {
            isSwitchOnLeftSide = gameData.charAt(0) == 'L';
            isScaleOnLeftSide = gameData.charAt(1) == 'L';
        }
    }

    public boolean getIsSwitchOnLeftSide() {
        return isSwitchOnLeftSide;
    }

    public boolean getIsScaleOnLeftSide() {
    	return isScaleOnLeftSide;
    }
    
    // returns 1,2,3 from left to right DS
    public int getDriverLocation() {
        return DriverStation.getInstance().getLocation();
    }

    @Override
	public void initialize() {
	}

	@Override
	public boolean tick() {
		return false;
	}

	@Override
	public void cleanup() {
	}

}
