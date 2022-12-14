/**
 * @file CalculMain.java
 * @author vincent.buniazet
 * @date 13/12/2022
 * @brief permet de réaliser le calcul d'approximation d'un incendie
 */

package Controler;
import Modele.*;

import java.lang.Math;
import java.util.*;

import Service.*;

public class CalculMain {
	
	
	private int nbPoint = 0;
	
	public CalculMain()
	{
		double couple[] = new double[2];
		ApproximerIncendie calc = new ApproximerIncendie();
		List<Trouple > tab = generateData();
		tab = retirerValeurNull(tab);
		tab = trierTabIntensiter(tab);
		
		List<Trouple > tabUnIncendie;
		
		//couple = calc.launchCalclul(tab,tab.size());
		while (tab.size() > 0)
		{
			tabUnIncendie = selectUnIncendie (tab);
			//System.out.println("Incendie en : " + "("+couple[0] + " " + couple[1]+")");
			
			if (tabUnIncendie.size() > 1)
			{
				if(tabUnIncendie.get(0).getIntensite() == tabUnIncendie.get(1).getIntensite())
				{
					couple[0] = tabUnIncendie.get(0).getx();
					couple[1] = tabUnIncendie.get(0).gety();
				}
				else
				{
					couple = calc.launchCalclul(tabUnIncendie,tabUnIncendie.size());
				}
			}
			else if (tabUnIncendie.size() == 1)
			{
				couple[0] = tabUnIncendie.get(0).getx();
				couple[1] = tabUnIncendie.get(0).gety();
			}
			
			System.out.println("Incendie en : " + "("+couple[0] + " " + couple[1]+") d'une intensité de : " +tabUnIncendie.get(0).getIntensite() );
		}
		
		
		
		
		
		
	}
	
	private List<Trouple > trierTabIntensiter(List<Trouple > tab)
	{
		List<Trouple > tabTrier = new ArrayList<Trouple >();
		while (tab.size() > 0)
		{
			int index  = maxTab(tab);
			tabTrier.add(tab.get(index));
			tab.remove(index);
		}
		return tabTrier;
	}
	
	private int maxTab(List<Trouple > tab)
	{
		int index = 0;
		double maxVal = tab.get(0).getIntensite();
		for (int i = 0; i < tab.size(); i++)
		{
			if (maxVal < tab.get(i).getIntensite())
			{
				maxVal = tab.get(i).getIntensite();
				index = i;
			}
		}
		return index;
	}
	
	private int indexPointPlusProche (List<Trouple > tab, Trouple point)
	{
		int index = -1;
		boolean drap = false;
		double distanceMin = Math.sqrt(Math.pow(point.getx() - tab.get(0).getx(), 2) + Math.pow(point.gety() - tab.get(0).gety(), 2) );
		for (int i = 0; i < tab.size(); i++)
		{
			
			if (distanceMin >= Math.sqrt(Math.pow(point.getx() - tab.get(i).getx(), 2) + Math.pow(point.gety() - tab.get(i).gety(), 2) ) && Math.sqrt(Math.pow(point.getx() - tab.get(i).getx(), 2) + Math.pow(point.gety() - tab.get(i).gety(), 2) ) < 10)
			{
				if (!drap && distanceMin == Math.sqrt(Math.pow(point.getx() - tab.get(i).getx(), 2) + Math.pow(point.gety() - tab.get(i).gety(), 2) ))
				{
					distanceMin = Math.sqrt(Math.pow(point.getx() - tab.get(i).getx(), 2) + Math.pow(point.gety() - tab.get(i).gety(), 2) );
					index = i;
					
				}
				else if (!drap)
				{
					distanceMin = Math.sqrt(Math.pow(point.getx() - tab.get(i).getx(), 2) + Math.pow(point.gety() - tab.get(i).gety(), 2) );
					index = i;
				}
			}
		}
		
		return index;
	}
	
	private List<Trouple > selectUnIncendie (List<Trouple > tab)
	{
		double distance = -1;
		double distancePrec = 0;
		List<Trouple > tabUnIncendie = new ArrayList<Trouple >();
		tabUnIncendie.add(tab.get(0));
		tab.remove(0);
		int i = 0;
		
		if (tab.size() > 0)
		{
			//distance = Math.sqrt(Math.pow(tabUnIncendie.get(0).getx() - tab.get(i).getx(), 2) + Math.pow(tabUnIncendie.get(0).gety() - tab.get(i).gety(), 2));
			distancePrec = 0;
			i = indexPointPlusProche (tab,tabUnIncendie.get(0));
			
			if (i >= 0 && i < tab.size())
			{
				distance = Math.sqrt(Math.pow(tabUnIncendie.get(0).getx() - tab.get(i).getx(), 2) + Math.pow(tabUnIncendie.get(0).gety() - tab.get(i).gety(), 2));
			}
		}
		
		//System.out.println("i " + i);
		
		while ( i >= 0 && tab.size() > 0 && distancePrec <= distance  && tab.get(i).getIntensite() <= tabUnIncendie.get(tabUnIncendie.size()-1).getIntensite())
		{
			distance = Math.sqrt(Math.pow(tabUnIncendie.get(0).getx() - tab.get(i).getx(), 2) + Math.pow(tabUnIncendie.get(0).gety() - tab.get(i).gety(), 2));
			//System.out.println("distancePrec : " + distancePrec + "; Distance : " + distance);
			
			if (distancePrec <= distance && distance < 10)
			{
				distancePrec = Math.sqrt(Math.pow(tabUnIncendie.get(0).getx() - tab.get(i).getx(), 2) + Math.pow(tabUnIncendie.get(0).gety() - tab.get(i).gety(), 2));
				tabUnIncendie.add(tab.get(i));
				//System.out.println("ajout");
				tab.remove(i);
			}
			if (tab.size()>0)
			{
				i = indexPointPlusProche (tab,tabUnIncendie.get(0));
				if (i < tab.size() && i >= 0)
				{
					distance = Math.sqrt(Math.pow(tabUnIncendie.get(0).getx() - tab.get(i).getx(), 2) + Math.pow(tabUnIncendie.get(0).gety() - tab.get(i).gety(), 2));
				}
			}
			
		}
		
		return tabUnIncendie;
	}
	
	private List<Trouple> retirerValeurNull(List<Trouple > tab)
	{
		int i = tab.size()-1;
		while (i >= 0)
		{
			if (tab.get(i).getIntensite() <= 0)
			{
				tab.remove(i);
			}
			i--;
			
		}
		nbPoint = tab.size(); 
		
		return tab;

	}
	
	private List<Trouple > generateData()
	{
		List<Trouple > tab = new ArrayList<Trouple >();
		
		tab.add(new Trouple(24,4,8));
		tab.add(new Trouple(22,6,6));
		tab.add(new Trouple(26,6,6));
		
		tab.add(new Trouple(2,3,0));
		tab.add(new Trouple(1,3,0));
		
		tab.add(new Trouple(1,2,8));
		tab.add(new Trouple(2,3,7));
		tab.add(new Trouple(3,3,6));
		
		tab.add(new Trouple(50,3,0));
		tab.add(new Trouple(52,3,0));
		
		tab.add(new Trouple(28,8,8));
		tab.add(new Trouple(30,10,6));
		tab.add(new Trouple(30,6,6));
		
		
		tab.add(new Trouple(-20,-20,1));
		
		tab.add(new Trouple(1,30,1));
		tab.add(new Trouple(2,26,2));
		nbPoint = tab.size();
		return tab;
	}
	
	

}
