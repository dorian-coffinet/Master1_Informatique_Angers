package monRobot;
import robocode.*;
import robocode.util.Utils;

import java.awt.Color;
import static robocode.util.Utils.normalRelativeAngleDegrees;

public class Chartiedor1 extends AdvancedRobot {



	/**
	 * Chartiedor1 - réalisé par Thierry, Dorian et Charly
					 à la date du 14 janvier 2013
	 */




	/**
	 * run: Chartiedor Comportement par défaut
	 */
	// plus grande dimension du champ e bataille
	public double dimensionMaxiChamp;
	// Changer de direction
    private byte inverserDirection = 1;

	public void run() 
	{	
		// Initialisation du robot
		setBodyColor(new Color(255, 229, 0));
		setGunColor(new Color(255, 229, 0));
		setRadarColor(Color.black);
		setScanColor(new Color(255, 229, 0));

		//Élements qui peuvent être utiles

		//Éléments concernant les configurations du Jeu
		double hauteurChampBataille = getBattleFieldHeight();//Dimension verticale du champs de bataille
		double largeurChampBataille = getBattleFieldWidth();//Dimension horizontale du champs de bataille

		out.println("largeur champ:"+getBattleFieldWidth());
		out.println("hauteur champ:"+getBattleFieldHeight());
		out.println("position X:"+getX());
		out.println("position Y:"+getY());
		out.println("angle du chassis au depart:"+getHeading());

		//Élément concernant le Robot
		double positionX = getX();//Position X du Robot
		double positionY = getY();//Position Y du Robot
		


		// on impose un déplacement si la position de départ choisi aleatoirement se situe dans le rectangle central de 60% de large et de 50% de hauteur

		if((positionX>(largeurChampBataille*0.2) & positionX<(largeurChampBataille*0.8))
				& (positionY>(hauteurChampBataille/4) & positionY<(hauteurChampBataille*0.75)))
		{

	// tourner le chassis pour etre perpendiculaire au  mur le plus proche de l'axe initial
			turnLeft(getHeading() % 90);
			out.println("angle du chassis:"+getHeading());
	//se deplacer vers le mur AV ou AR le plus proche
	// jusqu'à 10% de la distance du mur pour ne pas le toucher
			if(Math.round(getHeading())==0){	// si le char est orienté vers le Nord
				if(positionY<hauteurChampBataille/2) // s'il est plus près du Sud
					back(-hauteurChampBataille*0.1+positionY); // recule
				else
					ahead(hauteurChampBataille*0.9-positionY); // sinon avance vers le nord
			}	
			else if (Math.round(getHeading())==180){  	// si le char est orienté vers le Sud
				if(positionY<hauteurChampBataille/2)	// s'il est plus près du Sud
					ahead(positionY-hauteurChampBataille*0.1); // il avance vers le sud
				else
					back(-positionY+hauteurChampBataille*0.9); // sinon il recule
			}
			else if (Math.round(getHeading())==90){		// si le char est orienté vers l'Est
				if(positionX<largeurChampBataille/2)	// s'il est plus près de l'Ouest
					back(-largeurChampBataille*0.1 + positionX); // il recule
				else
					ahead(largeurChampBataille*0.9 - positionX); // il avance vers l'Est
			}
			else if (Math.round(getHeading())==270){	// si le char est orienté vers l'Ouest
				if(positionX<largeurChampBataille/2)	// s'il est plus près de l'Ouest
					ahead(positionX-largeurChampBataille*0.1); // il avance vers l'Ouest
				else
					back(-positionX+largeurChampBataille*0.9); // sinon il recule
			}
			// tourner le canon de 90°
			turnGunRight(90);
			// tourner le tank a droite de 90°
			turnRight(90);	
		}

	// on impose un déplacement si la position de départ choisi aleatoirement est trop près d'un mur.

		if(positionX<largeurChampBataille*0.1){	// si trop près du mur Ouest
	 
			if(getHeading()<90) turnRight(-getHeading()+90); // s'il est orienté entre 0 et 90°, tourne à droite pour etre dos au mur perpendiculairement
			else 
				if(getHeading()<270) turnLeft(getHeading()-90); // si orienté entre 90 et 270°, tourne à gauche
				else turnRight(450-getHeading()); // si orienté entre 270 et 360°, tourne à droite
			ahead(largeurChampBataille*0.1-positionX); // avance pour se placer à 10% du mur
			out.println("je me décollle du mur vers l'Est de "+(largeurChampBataille*0.1-positionX));
		}
		else 
			if(positionX>largeurChampBataille*0.9){		// si trop près du mur Est
				if(getHeading()<90)turnLeft(getHeading()+90);
				else 
					if (getHeading()<270) turnRight(270-getHeading());
					else turnLeft(getHeading()-270);
				ahead(positionX -largeurChampBataille*0.9);
				out.println("je me décollle du mur vers l'Ouest de "+(-largeurChampBataille*0.1+positionX));
			}

		if(positionY<hauteurChampBataille*0.1){	// si trop près du mur Sud
			if(getHeading()<180) turnLeft(getHeading());
			else 
				turnRight(-getHeading()+360);
			ahead(hauteurChampBataille*0.1-positionY);
			out.println("je me décollle du mur vers le nord de "+(hauteurChampBataille*0.1-positionY));
		}
		else
			if(positionY>hauteurChampBataille*0.9){	// si trop près du mur Nord
				if(getHeading()<180) turnRight(180-getHeading());
				else 
					turnLeft(getHeading()-180);
				ahead(positionY-hauteurChampBataille*0.9);
				out.println("je me décollle du mur vers le Sud de "+(-hauteurChampBataille*0.1+positionY));
			}	


		// Boucle principale
		while(true) 
		{
			setTurnRadarRight(10);//On tourne le radar vers la droite de 10 degrés
			turnGunRight(10);//On tourne le canon vers la droite de 10 degrés
		}
	}

	/**
	 * onScannedRobot: Lorsqu'un robot adverse est scanné
	 */
	public void onScannedRobot(ScannedRobotEvent e) 
	{
		// Alignement du radar avec l'adversaire
		double angleAlignementRadar = getHeadingRadians() + e.getBearingRadians() - getRadarHeadingRadians();
		setTurnRadarRightRadians(1.9 * Utils.normalRelativeAngle(angleAlignementRadar)); // coef de 1 à 2 à tester avec stats pour optimisation

		if (e.getDistance() > 150)// Si le robot est trop loin on se rapproche et on oriente aussi le canon
		{
			double angleAlignementCanon = normalRelativeAngleDegrees(e.getBearing() + (getHeading() - getRadarHeading()));

			setTurnGunRight(angleAlignementCanon); // On oriente le canon vers la cible
			setTurnRight(e.getBearing()); // On oriente le chassis vers la cible

			setAhead(e.getDistance() - 30);// On avance vers la cible
		}
		else
		{
			// Calcule le mouvement de l'ennemi et oriente le canon avant de tirer
			double angleAbsoluAdverse = getHeadingRadians() + e.getBearingRadians();
			setTurnGunRightRadians(Utils.normalRelativeAngle(angleAbsoluAdverse - getGunHeadingRadians() + (e.getVelocity() * Math.sin(e.getHeadingRadians() - angleAbsoluAdverse) / 13.0)));
			fire(3.0);

			// Si le robot est arrêté (Bloqué par un obstacle), on change de direction
			if (getVelocity() == 0)
				inverserDirection *= -1;

			// On encercle l'ennemi
			setTurnRight(e.getBearing() + 90);
			setAhead(1000 * inverserDirection);


		}
	}
	/**
	 * onHitRobot: Heurté contre un robot
	 */   

	public void onHitRobot(HitRobotEvent e)
	{


		// Alignement du radar avec l'adversaire
		double radarTurn = getHeadingRadians() + e.getBearingRadians() - getRadarHeadingRadians();
		setTurnRadarRightRadians(1.9 * Utils.normalRelativeAngle(radarTurn));
		scan();
	}
	



}

