package Controler;
import Modele.*;

import java.lang.Math;
import java.util.*;
import java.util.ArrayList;

public class CalculMain {
	
	private int rayon_max = 9;
	
	public CalculMain()
	{
		
	}
	
	public void launchCalclul()
	{
		double couple[];
		couple = new double[2];
		
		System.out.println("Je lance le calcul");
		Trouple tab[] = generateData();
		for (int i = 0; i < 5; i++)
		{
			System.out.println(tab[i]);
		}
		
		couple = intersection(tab);
		System.out.println(couple[0] + " " + couple[1]);
		
		/*double intersection[][];
		intersection = new double[2][2];
		intersection = intersectionDeuxCercle(tab[2],tab[4]);
		System.out.println(appartientToutCercle(intersection[0],tab)) ;
	*/}
	
	private double  anglePoint(double[]point1, double[] point2, double[] point3)
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
		System.out.println( "DBC: " + theta  );
		return theta;
	}
	
	
	private ArrayList<Double> anglesPoints(double[] pointRef , List<double[]> myList)
	{
		ArrayList<Double> angles = new ArrayList<Double>();
		
		for(int i = 1; i < myList.size(); i++)
		{
			angles.add(anglePoint(myList.get(0),pointRef , myList.get(i)));
		}
		
		return angles;
	}
	
	private int indexMin(ArrayList<Double> angles)
	{
		double min = 381;
		int index = 0;
		for (int i = 0; i < angles.size(); i++)
		{
			if (min > angles.get(i))
			{
				min = angles.get(i);
				index = i;
			}
		}
		return index;
	}
	
	private List<double[]>  sortPoint(double[]pointRef, List<double[]> myList)
	{
		List<double[]> myListTrier = new ArrayList<double[]>();
		ArrayList<Double> angles = anglesPoints(pointRef ,myList);
		
		myListTrier.add(myList.get(0));
		myList.remove(0);
		int index;
		while (myList.size() > 0)
		{
			for (int i = 0; i < myList.size(); i++)
			{
				index = indexMin(angles);
				
				myListTrier.add(myList.get(index));
				myList.remove(index);
				angles.remove(index);
			}
		}
		return myListTrier;
	}
	
	private double[] intersection(Trouple tab[])
	{
		double couple[];
		couple = new double[2];
		
		
		double intersection[][];
		intersection = new double[2][2];
		intersection = intersectionDeuxCercle(tab[0],tab[1]);
		
		List<double[]> myList = new ArrayList<double[]>();
		
		for ( int i = 0; i < 5; i++)
		{
			for ( int j = i+1; j < 5; j++)
			{
					intersection = intersectionDeuxCercle(tab[i],tab[j]);
					if (appartientToutCercle(intersection[0],tab))
					{
						myList.add(intersection[0]);
					}
					if (appartientToutCercle(intersection[1],tab))
					{
						myList.add(intersection[1]);
					}
			}
		}
		
		couple = moyennePoint(myList);
		myList = sortPoint(couple,myList);
		
		//System.out.println("point moyenb : (" + couple[0] + "," + couple[1] + ")" );
		couple = calculCentreGravite(myList);
		for (int e = 0; e < myList.size(); e++)
		{
			System.out.print("(" + myList.get(e)[0] +","+ myList.get(e)[1] + ") , ");
		}
		System.out.println("");
		
		return couple;
	}
	
	private double []calculCentreGravite(List<double[]> myList)
	{

		double couple[];
		/*couple = new double[2];
		couple[0] = -1;
		couple[1] = 2;
		myList.set(0, couple);

		couple = new double[2];
		couple[0] = 6;
		couple[1] = -1;
		myList.set(3, couple);
		couple = new double[2];
		couple[0] = 3;
		couple[1] = 1;
		myList.set(4, couple);
		couple = new double[2];
		couple[0] = 7;
		couple[1] = 5;
		myList.set(1, couple);
		couple = new double[2];
		couple[0] = 4;
		couple[1] = 3;
		myList.set(2, couple);*/
		
		//double couple[];
		
		/*couple = myList.get(2);
		myList.set(2,myList.get(4));
		myList.set(4,couple);*/
		couple = new double[2];
		
		couple[0] = 0;
		couple[1] = 0;
		
		double a = 0;
		
		for (int i = 0; i < myList.size(); i++)
		{
			System.out.print("(" + myList.get(i)[0] +","+ myList.get(i)[1] + ") , ");
			System.out.println("");
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
			//System.out.println("i : " + i +", a : " + a );
		}
		
		a = a /2;
		couple[0] = couple[0] / (6*a);
		couple[1] = couple[1] / (6*a);
		
		//System.out.println("("+couple[0]+ "," + couple[1] + ")");
		System.out.println(a);
		return couple;
	}
	
	private double[] moyennePoint(List<double[]> myList)
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
	
	private boolean appartientToutCercle(double point[],Trouple tab[])
	{
		boolean drap = true;
		int i = 0;
		while (i < 5 && drap)
		{
			double d = Math.sqrt((Math.pow(tab[i].getx()-point[0],2))+(Math.pow(tab[i].gety()-point[1],2)));
			if (d > (rayon_max - tab[i].getIntensite()) + 0.1)
			{
				//System.out.print(i);
				drap = false;
			}
			i++;
		}
		return drap;
	}
	
	private double[][] intersectionDeuxCercle(Trouple cercle1, Trouple cercle2)
	{
		
		double intersection[][];
		intersection = new double[2][2];
		
		int r1 = rayon_max - cercle1.getIntensite();
		int r2 = rayon_max - cercle2.getIntensite();
		
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
	
	private Trouple[] generateData()
	{
		Trouple tab[];
		tab = new Trouple[5];
		tab[0] = new Trouple(7,5,4);
		tab[1] = new Trouple(5,4,4);
		tab[2] = new Trouple(6,3,5);
		tab[3] = new Trouple(8,3,4);
		tab[4] = new Trouple(4,2,3);
		return tab;
	}

}
