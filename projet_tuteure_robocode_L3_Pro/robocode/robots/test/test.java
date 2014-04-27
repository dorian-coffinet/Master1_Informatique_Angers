package test;
import robocode.*;
import robocode.util.Utils;

import java.awt.Color;

public class test extends AdvancedRobot {


	public void run() 
	{	
	
		setAllColors(new Color(204,0,153));
	
		setAdjustRadarForRobotTurn(true);
        setAdjustRadarForGunTurn(true);
        setAdjustGunForRobotTurn(true);
	
		 while (true) {
			turnRadarRight(10);
		 
			turnRight(10);
		}	
	}

	
	
	public void onScannedRobot(ScannedRobotEvent e){
				setAhead(e.getDistance() - 100);
 				double angleAbsoluAdverse = getHeadingRadians() + e.getBearingRadians();
                setTurnGunRightRadians(Utils.normalRelativeAngle(angleAbsoluAdverse - getGunHeadingRadians() + (e.getVelocity() * Math.sin(e.getHeadingRadians() - angleAbsoluAdverse) / 16.7)));
				double angleAlignementRadar = getHeadingRadians() + e.getBearingRadians() - getRadarHeadingRadians();
        		setTurnRadarRightRadians(1.9 * Utils.normalRelativeAngle(angleAlignementRadar));		
				setTurnRight(e.getBearing()); 		
				fire(3);
				scan();
	} 
	
			
}