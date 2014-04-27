package CTD;
import robocode.*;
import robocode.util.Utils;

import java.awt.Color;
import static robocode.util.Utils.normalRelativeAngleDegrees;

public class CharthiedorV2 extends AdvancedRobot 
{
	 /**
     * Chartiedor1 - r�alis� par Thierry, Dorian et Charly
                     � la date du 14 janvier 2013
     */

    // Plus grande dimension du champ de bataille
    public double dimensionMaxiChamp;
    // Changer de direction
    private byte inverserDirection = 1;
    private double compteurHeurteParObus = 0, compteurNbDegre = 0;


    public void run() 
    {      	
        //�l�ments concernant les configurations du Jeu
        double hauteurChampBataille = getBattleFieldHeight();//Dimension verticale du champs de bataille
        double largeurChampBataille = getBattleFieldWidth();//Dimension horizontale du champs de bataille
    	
        // Initialisation du robot
        setBodyColor(new Color(255, 229, 0));
        setGunColor(new Color(255, 229, 0));
        setRadarColor(Color.black);
        setScanColor(new Color(255, 229, 0));

        // Radar ind�pendant
        setAdjustRadarForRobotTurn(true);
        setAdjustRadarForGunTurn(true);
        setAdjustGunForRobotTurn(true);

        //�l�ment concernant le Robot
        double positionX = getX();//Position X du Robot
        double positionY = getY();//Position Y du Robot
        
        // on impose un d�placement si la position de d�part choisi aleatoirement se situe dans le rectangle central de 60% de large et de 50% de hauteur
        if((positionX>(largeurChampBataille*0.2) & positionX<(largeurChampBataille*0.8))
                & (positionY>(hauteurChampBataille/4) & positionY<(hauteurChampBataille*0.75)))
        {

        	// tourner le chassis pour etre perpendiculaire au  mur le plus proche de l'axe initial
            turnLeft(getHeading() % 90);
            //se deplacer vers le mur AV ou AR le plus proche
            // jusqu'� 10% de la distance du mur pour ne pas le toucher
            if(Math.round(getHeading())==0){    // si le char est orient� vers le Nord
                if(positionY<hauteurChampBataille/2) // s'il est plus pr�s du Sud
                    back(-hauteurChampBataille*0.1+positionY); // recule
                else
                    ahead(hauteurChampBataille*0.9-positionY); // sinon avance vers le nord
            }    
            else if (Math.round(getHeading())==180){      // si le char est orient� vers le Sud
                if(positionY<hauteurChampBataille/2)    // s'il est plus pr�s du Sud
                    ahead(positionY-hauteurChampBataille*0.1); // il avance vers le sud
                else
                    back(-positionY+hauteurChampBataille*0.9); // sinon il recule
            }
            else if (Math.round(getHeading())==90){        // si le char est orient� vers l'Est
                if(positionX<largeurChampBataille/2)    // s'il est plus pr�s de l'Ouest
                    back(-largeurChampBataille*0.1 + positionX); // il recule
                else
                    ahead(largeurChampBataille*0.9 - positionX); // il avance vers l'Est
            }
            else if (Math.round(getHeading())==270){    // si le char est orient� vers l'Ouest
                if(positionX<largeurChampBataille/2)    // s'il est plus pr�s de l'Ouest
                    ahead(positionX-largeurChampBataille*0.1); // il avance vers l'Ouest
                else
                    back(-positionX+largeurChampBataille*0.9); // sinon il recule
            }   
        }

        // On impose un d�placement si la position de d�part choisi aleatoirement est trop pr�s d'un mur
        if(positionX<largeurChampBataille*0.1)
        {    // si trop pr�s du mur Ouest 
	            if(getHeading()<90) turnRight(-getHeading()+90); // s'il est orient� entre 0 et 90�, tourne � droite pour etre dos au mur perpendiculairement
	            else 
	                if(getHeading()<270) turnLeft(getHeading()-90); // si orient� entre 90 et 270�, tourne � gauche
	                else turnRight(450-getHeading()); // si orient� entre 270 et 360�, tourne � droite
	            ahead(largeurChampBataille*0.1-positionX); // avance pour se placer � 10% du mur
	        }
	        else 
	            if(positionX>largeurChampBataille*0.9){        // si trop pr�s du mur Est
	                if(getHeading()<90)turnLeft(getHeading()+90);
	                else 
	                    if (getHeading()<270) turnRight(270-getHeading());
	                    else turnLeft(getHeading()-270);
	                ahead(positionX -largeurChampBataille*0.9);
	            }
	
	        if(positionY<hauteurChampBataille*0.1){    // si trop pr�s du mur Sud
	            if(getHeading()<180) turnLeft(getHeading());
	            else 
	                turnRight(-getHeading()+360);
	            ahead(hauteurChampBataille*0.1-positionY);
	        }
	        else
	            if(positionY>hauteurChampBataille*0.9){    // si trop pr�s du mur Nord
	                if(getHeading()<180) turnRight(180-getHeading());
	                else 
	                    turnLeft(getHeading()-180);
	                ahead(positionY-hauteurChampBataille*0.9);
        }
   	        
        // Boucle principale
        while(true) 
        {
            turnRadarRight(10);//On tourne le radar vers la droite de 10 degr�s
			compteurNbDegre += 10;
			
			// Si on � fait un tour complet sans avoir scann� ou heurt� un ennemi, on bouge
			if(compteurNbDegre > 360)
				setAhead(getBattleFieldWidth()*0.01);
			// Si notre vitesse est nulle en heurtant un mur, on tourne
			if (getVelocity()==0) setTurnRight(120);
        }
    }

    /**
     * onScannedRobot: Lorsqu'un robot adverse est scann�
     */
    public void onScannedRobot(ScannedRobotEvent e) 
    {
        // Alignement du radar avec l'adversaire
        double angleAlignementRadar = getHeadingRadians() + e.getBearingRadians() - getRadarHeadingRadians();
        setTurnRadarRightRadians(1.9 * Utils.normalRelativeAngle(angleAlignementRadar)); // coef de 1 � 2 � tester avec stats pour optimisation
        compteurNbDegre = 0;// Mise � zero du compteur puisque l'on a scann� quelqu'un
        
        if (e.getDistance() > 300)// Si le robot est trop loin on se rapproche et on oriente aussi le canon
        {
        	if(e.getVelocity() == 0)
        	{
        		// Calcule le mouvement de l'ennemi et oriente le canon avant de tirer
                double angleAbsoluAdverse = getHeadingRadians() + e.getBearingRadians();
                setTurnGunRightRadians(Utils.normalRelativeAngle(angleAbsoluAdverse - getGunHeadingRadians()));
        	}
        	else
        	{
        		// Calcule le mouvement de l'ennemi et oriente le canon avant de tirer
                double angleAbsoluAdverse = getHeadingRadians() + e.getBearingRadians();
                setTurnGunRightRadians(Utils.normalRelativeAngle(angleAbsoluAdverse - getGunHeadingRadians() + (e.getVelocity() * Math.sin(e.getHeadingRadians() - angleAbsoluAdverse) / 16.7)));
        	}
            fire(1.1);
            
            double angleAlignementCanon = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));

            setTurnGunRight(angleAlignementCanon); // On oriente le canon vers la cible
            setTurnRight(e.getBearing()); // On oriente le chassis vers la cible

            setAhead(e.getDistance() - 100);// On avance vers la cible
        }
        else
        {
        	// Calcule le mouvement de l'ennemi et oriente le canon avant de tirer
            double angleAbsoluAdverse = getHeadingRadians() + e.getBearingRadians();
            setTurnGunRightRadians(Utils.normalRelativeAngle(angleAbsoluAdverse - getGunHeadingRadians() + (e.getVelocity() * Math.sin(e.getHeadingRadians() - angleAbsoluAdverse) / 11)));
            fire(3.0);
            
            if(getVelocity() == 0)
            	inverserDirection *= -1;

            // On encercle l'ennemi
            setTurnRight(e.getBearing() + 90);
            setAhead(1000 * inverserDirection);
        }
    }
    
    /**
     * onHitRobot: Heurt� contre un robot
     */   
    public void onHitRobot(HitRobotEvent e)
    {
    	compteurNbDegre = 0;// Mise � zero du compteur puisque l'on a heurt� quelqu'un

    	// Alignement du radar avec l'adversaire
        double radarTurn = getHeadingRadians() + e.getBearingRadians() - getRadarHeadingRadians();
        setTurnRadarRightRadians(1.9 * Utils.normalRelativeAngle(radarTurn));
        scan();
    }
	
	public void onHitByBullet(HitByBulletEvent e)
	{
		int bord= 21; //marge du bord que l'on s'autorise pour eviter un axe de tir 
		double hauteurChampBataille = getBattleFieldHeight();//Dimension verticale du champs de bataille
        double largeurChampBataille = getBattleFieldWidth();//Dimension horizontale du champs de bataille
		compteurHeurteParObus ++;
		if(compteurHeurteParObus > 2)// la 3eme fois que l'on est touche par un obus
		{
			compteurHeurteParObus = 0;
			// si l'on est pas trop pres d'un bord, on zig-zag
			if((getX()>bord && getX()<largeurChampBataille-bord)&&(getY()>bord && getY()<hauteurChampBataille-bord))
			{
				setTurnLeft(45);
				setAhead(20);
				setTurnRight(45);
			}
			else // sinon on tourne a 90� et on avance
			{
				setTurnRight(90);
				setAhead(20);	
			}
		}
	}
}					 