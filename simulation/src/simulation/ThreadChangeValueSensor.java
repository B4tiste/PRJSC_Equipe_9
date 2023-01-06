package simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import Modele.Flamme;
import Modele.Trouple;
import Modele.Feu;
import Service.*;

public class ThreadChangeValueSensor extends Thread {
	
	ArrayBlockingQueue<List<Feu>>  queue;
	AtomicBoolean enFonction;
	
	List<Feu> fires;
	List<Trouple> tab;
	
	AccesDataSimulation db_s;

	
    public ThreadChangeValueSensor (ArrayBlockingQueue<List<Feu>>  cld,AtomicBoolean enFonction) {
        this.queue = cld;
        this.enFonction = enFonction;
      
        db_s = new AccesDataSimulation();
        db_s.createSensors ();
		
		
		
        
    }
    
    public void run() 
    {
    	while (enFonction.getAndSet(true))
    	{
    		try {
				fires = queue.take();
				tab = db_s.getSensors();
				
				//resetValueSensor (tab);
				calValSensor (tab,fires);
				System.out.println(tab);
				System.out.println(fires);
				
				db_s.saveValuesSensors(tab);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		//System.out.println("OK2");
    	}
    	
    	enFonction.set(false);
    }
	
	private void calValSensor (List<Trouple> tab,List<Feu> fires)
	{
		for (int i = 0; i < fires.size(); i++)
		{
			for (int j = 0; j < fires.get(i).sizeFeu(); j++)
			{
				for (int g = 0; g < tab.size(); g++)
				{
					double distance = Math.sqrt(Math.pow(tab.get(g).getx()-fires.get(i).getFlamme(j).getx(),2) + Math.pow(tab.get(g).gety()-fires.get(i).getFlamme(j).gety(),2));
					int newIntensite = fires.get(i).getFlamme(j).getIntensite() - (int)(distance);
					//System.out.println("distance : " + distance);
					if (distance <= fires.get(i).getFlamme(j).getDistanceDetectable() && newIntensite > tab.get(g).getIntensite())
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
