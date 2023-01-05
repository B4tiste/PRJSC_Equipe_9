package simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import Modele.Flamme;
import Modele.Trouple;

public class ThreadChangeValueSensor extends Thread {
	
	ArrayBlockingQueue<List<List<Flamme>>>  queue;
	AtomicBoolean enFonction;
	
	List<List<Flamme>> fires;
	List<Trouple> tab;

	
    public ThreadChangeValueSensor (ArrayBlockingQueue<List<List<Flamme>>>  cld,AtomicBoolean enFonction) {
        this.queue = cld;
        this.enFonction = enFonction;
      
		tab = generateSensor();
        
    }
    
    public void run() 
    {
    	while (enFonction.getAndSet(true))
    	{
    		try {
				fires = queue.take();
				resetValueSensor (tab);
				calValSensor (tab,fires);
				System.out.println(tab);
				System.out.println(fires);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		//System.out.println("OK2");
    	}
    	
    	enFonction.set(false);
    }
    
	
	private List<Trouple> generateSensor()
	{
		List<Trouple> tab  = new ArrayList<Trouple>();
		tab.add(new Trouple(2,2,0));
		tab.add(new Trouple(1,3,0));
		tab.add(new Trouple(1.5,2,0));
		tab.add(new Trouple(3.5,1,0));
		tab.add(new Trouple(5,5,0));
		tab.add(new Trouple(7,3,0));
		tab.add(new Trouple(-10.5,-10,0));
		tab.add(new Trouple(-9,-10,0));
		tab.add(new Trouple(-9,-8,0));
		return tab;
	}
	
	private void calValSensor (List<Trouple> tab,List<List<Flamme>> fires)
	{
		for (int i = 0; i < fires.size(); i++)
		{
			for (int j = 0; j < fires.get(i).size(); j++)
			{
				for (int g = 0; g < tab.size(); g++)
				{
					double distance = Math.sqrt(Math.pow(tab.get(g).getx()-fires.get(i).get(j).getx(),2) + Math.pow(tab.get(g).gety()-fires.get(i).get(j).gety(),2));
					int newIntensite = fires.get(i).get(j).getIntensite() - (int)(distance);
					//System.out.println("distance : " + distance);
					if (distance <= fires.get(i).get(j).getDistanceDetectable() && newIntensite > tab.get(g).getIntensite())
					{
						tab.get(g).setIntensite(newIntensite);
					}
				}
			}
		}
	}
	
	private void resetValueSensor (List<Trouple> tab)
	{
		for (int i = 0; i < tab.size(); i++)
		{
			tab.get(i).setIntensite(0);
		}
	}

}
