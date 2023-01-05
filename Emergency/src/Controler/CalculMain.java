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
		
		List<List<Trouple> > incendiesZone= new ArrayList<List<Trouple> >();
		List<Trouple> incendieZone;
		
		int intensite = 0;
		
		
		
		while (tab.size() > 0)
		{
			incendieZone = detectIncendieZone(tab);
			if (incendieZone.size() > 1 && isInIncendieZone(incendieZone.get(0),incendiesZone) == -1)
			{
				incendiesZone.add(detectIncendieZone(tab));
			}
			
			tabUnIncendie = selectUnIncendie (tab);
			
			if (tabUnIncendie.size() > 1 && (incendieZone.size()>1 && incendieZone.indexOf(tabUnIncendie.get(0)) == -1 || incendieZone.size()<1))
			{
				if(tabUnIncendie.get(0).getIntensite() == tabUnIncendie.get(1).getIntensite())
				{
					couple[0] = tabUnIncendie.get(0).getx();
					couple[1] = tabUnIncendie.get(0).gety();
					intensite = tabUnIncendie.get(0).getIntensite() ;
				}
				else
				{
					couple = calc.launchCalclul(tabUnIncendie,tabUnIncendie.size());
					intensite = tabUnIncendie.get(0).getIntensite() ;
				}
			}
			else if (incendieZone.size() == 1 && tabUnIncendie.size() > 1)
			{
				int valIsInLangue = isInIncendieZone(incendieZone.get(0),incendiesZone) ;
				if(valIsInLangue == -1)
				{
					if(tabUnIncendie.get(0).getIntensite() == tabUnIncendie.get(1).getIntensite())
					{
						couple[0] = tabUnIncendie.get(0).getx();
						couple[1] = tabUnIncendie.get(0).gety();
						intensite = tabUnIncendie.get(0).getIntensite() ;
					}
					else
					{
						couple = calc.launchCalclul(tabUnIncendie,tabUnIncendie.size());
						intensite = tabUnIncendie.get(0).getIntensite() ;
					}
				}
				else
				{
					System.out.print("Zone ");
					couple = calc.launchCalculIncendieZone(incendiesZone.get(valIsInLangue));
					intensite = incendiesZone.get(valIsInLangue).get(0).getIntensite() * incendiesZone.get(valIsInLangue).size();
				}
			}
			else if (tabUnIncendie.size() == 1)
			{
				couple[0] = tabUnIncendie.get(0).getx();
				couple[1] = tabUnIncendie.get(0).gety();
				intensite = tabUnIncendie.get(0).getIntensite() ;
			}
			else
			{
				couple[0] = 0;
				couple[1] = 0;
				intensite = 0;
			}
			
			if (!(incendieZone.size()>1 && incendieZone.indexOf(tabUnIncendie.get(0)) != -1 ))
			{
				
				System.out.println("Incendie en : " + "("+couple[0] + " " + couple[1]+") d'une intensité de : " +intensite );
			}
			
			

		}
		
		
		
		
		
		
	}
	
	/**
	 * @param point un capteur qu'on veux vérifier s'il est dans une zone d'incendie
	 * @param incendiesZone les différentcapteur dans une zone d'incendue
	 * @return l'index (-1 s'il n'existe pâs)
	 */
	private int isInIncendieZone(Trouple point,List<List<Trouple> > incendiesZone)
	{
		int i = 0;
		int ret = -1;
		while (i < incendiesZone.size() && incendiesZone.get(i).indexOf(point) == -1)
		{
			i++;
		}
		if (i < incendiesZone.size() && incendiesZone.get(i).indexOf(point) != -1)
		{
			ret = i;
		}
		else 
		{
			ret = -1;
		}

		return ret;
	}
	
	/**
	 * @param tab
	 * @return
	 */
	private List<Trouple > detectIncendieZone(List<Trouple > tab)
	{
		List<Trouple > incendieZone = new ArrayList<Trouple >();
		int intensiter = tab.get(0).getIntensite();
		int i = 0;
		int j = 0;
		incendieZone.add(tab.get(0));
		for (j = 0; j < incendieZone.size(); j++)
		{
			i = 0;
			while (i < tab.size() && tab.get(i).getIntensite() == intensiter)
			{
				double distance =  Math.sqrt(Math.pow(incendieZone.get(j).getx() - tab.get(i).getx(), 2) + Math.pow(incendieZone.get(j).gety() - tab.get(i).gety(), 2) );
				System.out.println(distance);
				if (distance <= 2 && incendieZone.indexOf(tab.get(i)) == -1)
				{
					incendieZone.add(tab.get(i));
				}
				i++;
			}
			
		}
		//System.out.println("langue: "+  langue);
		return incendieZone;
	}
	
	/**
	 * @param tab la liste des capteur recu
	 * @return retourne la liste des capteurs recu par tirée par intensité
	 */
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
	
	/**
	 * @param tab la liste des capteurs reçu
	 * @return la valeur maximum de l'intensiter
	 */
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
	
	
	/**
	 * @param tab la liste des capteurs recu
	 * @param point le point pour lequel on recherche le point le plus proche
	 * @return l'index du point le plus proche du point en paramètre
	 */
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
	
	/**
	 * @param tab
	 * @return
	 */
	private List<Trouple > selectUnIncendie (List<Trouple > tab)
	{
		double distance = -1;
		double distancePrec = 0;
		List<Trouple > tabUnIncendie = new ArrayList<Trouple >();
		tabUnIncendie.add(tab.get(0));
		tab.remove(0);
		int i = 0;
		int iprec = -1;
		
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
		
		while ( i < tab.size() && i >= 0 && tab.size() > 0 && distancePrec <= distance  && tab.get(i).getIntensite() <= tabUnIncendie.get(tabUnIncendie.size()-1).getIntensite())
		{
			iprec = i;
			distance = Math.sqrt(Math.pow(tabUnIncendie.get(0).getx() - tab.get(i).getx(), 2) + Math.pow(tabUnIncendie.get(0).gety() - tab.get(i).gety(), 2));
			
			if (distancePrec <= distance && distance < tabUnIncendie.get(0).getIntensite() && distance < tab.get(i).getIntensite())
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
			if (iprec == i)
			{
				i ++;
			}
			
		}
		
		return tabUnIncendie;
	}
	
	/**
	 * @param tab le tableau de tout les capteurs reçu
	 * @return retourne la liste des trouple dont l'intensité est null
	 */
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
		
		/*tab.add(new Trouple(24,4,8));
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
		
		tab.add(new Trouple(103,103,9));
		tab.add(new Trouple(103,107,9));
		//tab.add(new Trouple(-20,-20,9));
		tab.add(new Trouple(103,105,9));
		tab.add(new Trouple(103,109,6));
		tab.add(new Trouple(101,107,6));
		tab.add(new Trouple(105,107,6));
		tab.add(new Trouple(101,105,6));
		tab.add(new Trouple(105,105,6));
		tab.add(new Trouple(101,103,6));
		tab.add(new Trouple(105,103,6));
		tab.add(new Trouple(103,101,6));
		tab.add(new Trouple(107,109,4));
		tab.add(new Trouple(107,107,4));
		tab.add(new Trouple(107,105,4));
		tab.add(new Trouple(107,103,4));*/
		
		tab.add(new Trouple(2.0,2.0,8));
		tab.add(new Trouple(1.0,3.0,7));
		tab.add(new Trouple(1.5,2.0,7));
		tab.add(new Trouple(3.5,1.0,7));
		tab.add(new Trouple(5.0,5.0,5));
		tab.add(new Trouple(7.0,3.0,5));
		
		tab.add(new Trouple(-10.5,-10.0,8));
		tab.add(new Trouple(-9.0,-10.0,7));
		tab.add(new Trouple(-9.0,-8.0,0));
		
		
		
		
		
		nbPoint = tab.size();
		return tab;
	}
	
	

}
