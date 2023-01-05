package Service;

import java.util.ArrayList;
import java.util.List;

import Modele.Trouple;

public class ApproximerIncendie {
	/**
	 * @brief permet de lancer le calcul pour trouver un approximation d'un incendie
	 */
	private int nbPoint = 0;
	
	public ApproximerIncendie()
	{
		
	}
	
	/**
	 * @param tab la liste des capteurs qui représente un incendie
	 * @param size le nombre de capteur
	 * @return les coordonée d'un probable incendie
	 */
	public double[] launchCalclul(List<Trouple > tab, int size)
	{
		this.nbPoint = size;
		double couple[];
		couple = new double[2];
		
		/*System.out.println("Je lance le calcul");
		
		for (int i = 0; i < nbPoint ; i++)
		{
			System.out.println(tab.get(i));
		}*/
		
		couple = intersection(tab);
		
		return couple;
		
	}
	
	/**
	 * @param tab 
	 * @return
	 */
	public double[] launchCalculIncendieZone(List<Trouple > tab)
	{
		double couple[];
		couple = new double[2];
		CalculGeneral calc = new CalculGeneral();
		
		couple[0] = tab.get(0).getx();
		couple[1] = tab.get(0).gety();
		
		List<double[] > newTabCouple = new ArrayList<double[]>();
		List<double[] > newTabSort;
		
		for ( int i = 0; i < tab.size(); i++)
		{
			couple = new double[2];
			couple[0] = tab.get(i).getx();
			couple[1] = tab.get(i).gety();
			newTabCouple.add(couple);
		}
		
		
		newTabSort = sortPoint(couple,newTabCouple);
		couple = calc.calculCentreGravite(newTabSort);
		
		return couple;
	}
	
	/**
	 * 
	 * @param pointRef le point de référence pour faire le calcul d'angle sur une liste de point (ABC) => (B)
	 * @param myList la liste deux point à calculer l'angle par rapport au point de ref et le 1er point de la liste
	 * @return la liste des angle entre le 1er point de la liste, le point de reference et les autres
	 * @brief Permet d'avoir un ensemble d'angle d'une liste de point par rapport à un point de référence
	 */
	private ArrayList<Double> anglesPoints(double[] pointRef , List<double[]> myList)
	{
		CalculGeneral calc = new CalculGeneral();
		ArrayList<Double> angles = new ArrayList<Double>();
		
		for(int i = 1; i < myList.size(); i++)
		{
			angles.add(calc.anglePoint(myList.get(0),pointRef , myList.get(i)));
		}
		
		return angles;
	}
	
	/**
	 * @param angles une liste de valeur d'angle 
	 * @return l'index de l'angle le plus petit
	 * @brief permet de trouver l'index du tableau pour lequel la valeur est la plus petite
	 */
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
	
	/**
	 * @param pointRef le point de référence pour effectuer le trie
	 * @param myList la liste de point à trier 
	 * @return la liste de point tirer sur un cercle trigonométrique autour du point de réference (par rapport à la droite entre le point de
	 * réference et le 1er point de la liste
	 */
	private List<double[]>  sortPoint(double[]pointRef, List<double[]> myList)
	{
		List<double[]> myListTrier = new ArrayList<double[]>();
		ArrayList<Double> angles = anglesPoints(pointRef ,myList);
		
		if (myList.size()>0)
		{
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
		}
		return myListTrier;
	}
	
	/**
	 * @param tab la liste de point pour laquelle on veut le point d'intersection
	 * @return le point d'intersection de ses point
	 * @brief permet d'approximer un point de rencontre entre tout les capeurs
	 */
	private double[] intersection(List<Trouple > tab)
	{
		CalculGeneral calc = new CalculGeneral();
		double couple[];
		couple = new double[2];
		
		double intersection[][];
		intersection = new double[2][2];
		
		List<double[]> myList = new ArrayList<double[]>();
		
		for ( int i = 0; i < nbPoint ; i++)
		{
			for ( int j = i+1; j < nbPoint ; j++)
			{
					intersection = calc.intersectionDeuxCercle(tab.get(i),tab.get(j));
					
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
		couple = calc.moyennePoint(myList);
		myList = sortPoint(couple,myList);
	
		couple = calc.calculCentreGravite(myList);
		
		return couple;
	}
	
	/**
	 * @param point les coordonnés d'un point
	 * @param tab une liste de donnés de capteur
	 * @return vrai si le point appartient à tout les disque données par les capteurs
	 */
	private boolean appartientToutCercle(double point[],List<Trouple > tab)
	{
		boolean drap = true;
		int i = 0;
		while (i < nbPoint && drap)
		{
			double d = Math.sqrt((Math.pow(tab.get(i).getx()-point[0],2))+(Math.pow(tab.get(i).gety()-point[1],2)));
			if (d > (tab.get(i).getRayon()) + 0.1)
			{
				drap = false;
			}
			i++;
		}
		return drap;
	}
	
}
