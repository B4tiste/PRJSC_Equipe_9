package Service;
import Modele.*;

import java.lang.Math;
import java.util.*;

public class CalculGeneral {
	
	
	public CalculGeneral()
	{
		
	}
	/**
	 * 
	 * @param point1 les coordonnées du 1er point (A)
	 * @param point2 les coordonnées du 2nd point (B)
	 * @param point3 les coordonnées du 3ème point (C)
	 * @return l'angle entre les 3 point (ABC)
	 * @brief permet de calculer l'angle (ABC) entre trois points avec leurs coodonées
	 */
	public double  anglePoint(double[]point1, double[] point2, double[] point3)
	{
		double a = Math.sqrt((Math.pow(point1[0]-point2[0],2))+(Math.pow(point1[1]-point2[1],2)));
		double b = Math.sqrt((Math.pow(point3[0]-point2[0],2))+(Math.pow(point3[1]-point2[1],2)));
		double c = Math.sqrt((Math.pow(point1[0]-point3[0],2))+(Math.pow(point1[1]-point3[1],2)));
		
		double angle = Math.acos( ( a*a + b*b - c*c ) / ( 2*a*b ) );
	
		double theta1 = Math.atan2 ( point1[1] - point2[1], point1[0] - point2[0]);
		double  theta2 = Math.atan2 ( point3[1] - point2[1], point3[0] - point2[0]);
		double theta = theta2 - theta1;
		
		if (Math.toDegrees(theta  ) <0 )
		{
			theta = 360 + Math.toDegrees(theta);
		}
		else
		{
			theta = Math.toDegrees(theta);
		}
		return theta;
	}
	
	/**
	 * @param myList la liste de point d'un polygone
	 * @return le point de gravité du polygone
	 * @brief permet de calculer le point de graviter d'un polygone
	 */
	public double []calculCentreGravite(List<double[]> myList)
	{

		double couple[];
		couple = new double[2];
		
		couple[0] = 0;
		couple[1] = 0;
		
		double a = 0;
		
		if (myList.size() >= 3)
		{
			for (int i = 0; i < myList.size(); i++)
			{
				if (i < myList.size() - 1)
				{
					a += (myList.get(i)[0]*myList.get(i+1)[1]) - (myList.get(i+1)[0]*myList.get(i)[1]);
					couple[0] += (myList.get(i)[0]+myList.get(i+1)[0]) * (myList.get(i)[0]*myList.get(i+1)[1]-myList.get(i+1)[0]*myList.get(i)[1]);
					couple[1] += (myList.get(i)[1]+myList.get(i+1)[1]) * (myList.get(i)[0]*myList.get(i+1)[1]-myList.get(i+1)[0]*myList.get(i)[1]);
					
				}
				else
				{
					a += (myList.get(i)[0]*myList.get(0)[1]) - (myList.get(0)[0]*myList.get(i)[1]);
					couple[0] += (myList.get(i)[0]+myList.get(0)[0]) * (myList.get(i)[0]*myList.get(0)[1]-myList.get(0)[0]*myList.get(i)[1]);
					couple[1] += (myList.get(i)[1]+myList.get(0)[1]) * (myList.get(i)[0]*myList.get(0)[1]-myList.get(0)[0]*myList.get(i)[1]);
					
				}
			}
		}
		else if (myList.size() == 2)
		{
			System.out.println("ici  " + myList.get(0)[0] + " " + myList.get(0)[1]);
			System.out.println("ici  " + myList.get(1)[0] + " " + myList.get(1)[1]);
			couple[0] = (myList.get(0)[0]+myList.get(1)[0])/2;
			couple[1] = (myList.get(0)[1]+myList.get(1)[1])/2;
		}
		else if (myList.size() == 1)
		{
			couple[0] = myList.get(0)[0];
			couple[1] = myList.get(0)[1];
		}
		
		if (a != 0)
		{
			a = a /2;
			couple[0] = couple[0] / (6*a);
			couple[1] = couple[1] / (6*a);
		}
		else 
		{
			couple[0] = couple[0] ;
			couple[1] = couple[1] ;
		}
		return couple;
	}
	
	/**
	 * @param cercle1 un trouple représentant un cercle avec ses coordonnés et son rayon
	 * @param cercle2 un trouple représentant un cercle avec ses coordonnés et son rayon
	 * @return les point d'intersection entre les deux cercle (max 2)
	 */
	public double[][] intersectionDeuxCercle(Trouple cercle1, Trouple cercle2)
	{
		double intersection[][];
		intersection = new double[2][2];
		
		int r1 = (int) cercle1.getRayon();
		int r2 = (int) cercle2.getRayon();
		
		double d = Math.sqrt((Math.pow(cercle2.getx()-cercle1.getx(),2))+(Math.pow(cercle2.gety()-cercle1.gety(),2)));
		double a = (Math.pow(r1,2) - Math.pow(r2,2) + Math.pow(d,2))/(2*d);
		double b = (Math.pow(r2,2) - Math.pow(r1,2) + Math.pow(d,2))/(2*d);

		double h = Math.sqrt(Math.pow(r1,2)-Math.pow(a, 2));
		
		double x = cercle1.getx() + ((a/d) * (cercle2.getx() - cercle1.getx()));
		double y = cercle1.gety() + (a/d) * (cercle2.gety() - cercle1.gety());
		
		
		intersection[0][0] = x + (h*(cercle2.gety()-cercle1.gety()))/d;
		intersection[0][1] = y - (h*(cercle2.getx()-cercle1.getx()))/d;
		
		intersection[1][0] = x - (h*(cercle2.gety()-cercle1.gety()))/d;
		intersection[1][1] = y + (h*(cercle2.getx()-cercle1.getx()))/d;
		
		return intersection;
	}
	
	/**
	 * @param myList un eliste de point données par leurs coordonnées
	 * @return la moyenne de tout ses point (c'est une approximation)
	 */
	public double[] moyennePoint(List<double[]> myList)
	{
		double couple[];
		couple = new double[2];
		couple[0] = 0;
		couple[1] = 0;
		for (int i = 0; i < myList.size(); i++)
		{
			couple[0] += myList.get(i)[0];
			couple[1] += myList.get(i)[1];
		}
		couple[0] /= myList.size();
		couple[1] /= myList.size();
		return couple;
	}

}
