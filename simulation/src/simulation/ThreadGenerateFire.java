package simulation;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

import Modele.Flamme;
import Modele.Feu;
import Service.AccesDataSimulation;


public class ThreadGenerateFire extends Thread {
	
	private ArrayBlockingQueue<List<Feu>>  queue;
	
	private List<Feu> fires;
	
	private double xMin = 0;
	private double xMax = 9;
	
	private double yMin = 1;
	private double yMax = 6;
	
	private double distanceUnitaire = 0.1;
	
	private int intensiteMax = 9;
	private double distanceMax = 9 * distanceUnitaire;
	
	AccesDataSimulation db_s;
	
	
	
	private AtomicBoolean enFonction;
	
    public ThreadGenerateFire (ArrayBlockingQueue<List<Feu>>  cld,AtomicBoolean enFonction) {
        this.queue = cld;
        this.enFonction = enFonction;
        db_s = new AccesDataSimulation();
        //fires = new ArrayList <List<Flamme>>(); 
        
        
    }
    
    public void run() {
       //System.out.println();
       while(enFonction.getAndSet(true))
       {
    	   
    	   try {
   			Thread.sleep(100);
	   		} catch (InterruptedException e) {
	   			// TODO Auto-generated catch block
	   			e.printStackTrace();
	   		}
    	   
    	   fires = db_s.getValuesFires();
    	   //System.out.println("Ok");
    	   queue.clear();
    	  
    	    try {
    	    	int val = (int)(Math.random()*(5-0));
    			if (val == 1)
    			{
    				System.out.println("Add");
    				addFire();
    			}
    			else
    			{
    				System.out.println("involve");
    				involeFire();
    			}
				queue.put(fires);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	    
    	    db_s.saveDataFires(fires);
       }
       enFonction.set(false);
    }
    
    
    
    
    
    private void involeFire()
    {
    	for ( int i = 0 ; i < fires.size(); i++ )
    	{
    		for (int j = 0; j < fires.get(i).sizeFeu();j++)
    		{
    			int val = (int)(Math.random()*(2-0));
    			if (val == 1)
    			{
    				
    				if (fires.get(i).getFlamme(j).getIntensite() <= 9)
    				{
    					fires.get(i).getFlamme(j).setIntensite(fires.get(i).getFlamme(j).getIntensite());
    				}
    				else
    				{
    					
    					fires.get(i).addFlamme(new Flamme(0,fires.get(i).getFlamme(j).getx()+1,fires.get(i).getFlamme(j).getx(),9,fires.get(i).getFlamme(j).getDistanceDetectable(),-1));
    				}
    			}
    			else
    			{
    				if (fires.get(i).getFlamme(j).getDistanceDetectable() + distanceUnitaire <= 9)
    				{
    					fires.get(i).getFlamme(j).setDistanceDetectable(fires.get(i).getFlamme(j).getDistanceDetectable()  + distanceUnitaire);
    				}
    				else
    				{
    					fires.get(i).addFlamme(new Flamme(0,fires.get(i).getFlamme(j).getx()+1,fires.get(i).getFlamme(j).getx(),9,fires.get(i).getFlamme(j).getDistanceDetectable(),-1));
    				}
    			}
    			
    			
    		}
    		
    	}
    }
    
    
    
    
    
	private void addFire()
	{
		double x = Math.random()*( xMax - xMin );
		double y = Math.random()*( yMax - yMin );
		
		int intensite = (int)(Math.random()*(intensiteMax-1));
		double distance = Math.random()*(distanceMax-intensite*distanceUnitaire );
		
		List<Flamme> flammes = new ArrayList<Flamme>();
		flammes.add(new Flamme(-1,5,5,intensite,distance,-1));
		Feu fire = new Feu(flammes,-1);
		fires.add(fire);
	}

}
