package THY;
import robocode.*;
import robocode.util.Utils;

import java.awt.Color;

public class THY extends AdvancedRobot {


	public void run() 
	{	
	
		setAllColors(new Color(51,255,51));
	
		setAdjustRadarForRobotTurn(true);
        setAdjustRadarForGunTurn(true);
        setAdjustGunForRobotTurn(true);
	
		 while (true) {
			turnRadarRight(10);
		 
			ahead(10);
		}	
	}

	public void onHitWall(HitWallEvent e){
				back(100);
 				turnRight(120);
				scan();
	}
	
	public void onHitRobot(HitRobotEvent e){
				double radarTurn = getHeadingRadians() + e.getBearingRadians() - getRadarHeadingRadians();
        setTurnRadarRightRadians(1.9 * Utils.normalRelativeAngle(radarTurn));

				scan();
	}
	
	public void onScannedRobot(ScannedRobotEvent e){
				double angleAlignementRadar = getHeadingRadians() + e.getBearingRadians() - getRadarHeadingRadians();
        		setTurnRadarRightRadians(1.9 * Utils.normalRelativeAngle(angleAlignementRadar));		
 				double angleAbsoluAdverse = getHeadingRadians() + e.getBearingRadians();
                setTurnGunRightRadians(Utils.normalRelativeAngle(angleAbsoluAdverse - getGunHeadingRadians() + (e.getVelocity() * Math.sin(e.getHeadingRadians() - angleAbsoluAdverse) / 16.7)));
				fire(3);
				setTurnRight(e.getBearing()); 		
				setAhead(e.getDistance() - 100);
	} 
	
	public void onHitByBullet(HitByBulletEvent e){
				
	}
	
}
				
