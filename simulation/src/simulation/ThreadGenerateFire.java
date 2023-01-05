package simulation;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
/*test*/ 
import Modele.Flamme;


public class ThreadGenerateFire extends Thread {
	
	private ArrayBlockingQueue<List<List<Flamme>>>  queue;
	
	private List<List<Flamme>> fires;
	
	private double xMin = 0;
	private double xMax = 9;
	
	private double yMin = 1;
	private double yMax = 6;
	
	private double distanceUnitaire = 1;
	
	private int intensiteMax = 9;
	private double distanceMax = 9 * distanceUnitaire;
	
	
	
	private AtomicBoolean enFonction;
	
    public ThreadGenerateFire (ArrayBlockingQueue<List<List<Flamme>>>  cld,AtomicBoolean enFonction) {
        this.queue = cld;
        this.enFonction = enFonction;
        fires = new ArrayList <List<Flamme>>(); 
        
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
       }
       enFonction.set(false);
    }
    
    
    
    
    
    private void involeFire()
    {
    	for ( int i = 0 ; i < fires.size(); i++ )
    	{
    		for (int j = 0; j < fires.get(i).size();j++)
    		{
    			int val = (int)(Math.random()*(2-0));
    			if (val == 1)
    			{
    				
    				if (fires.get(i).get(j).getIntensite() <= 9)
    				{
    					fires.get(i).get(j).setIntensite(fires.get(i).get(j).getIntensite());
    				}
    				else
    				{
    					
    					fires.get(i).add(new Flamme(fires.get(i).get(j).getx()+1,fires.get(i).get(j).getx(),9,fires.get(i).get(j).getDistanceDetectable()));
    				}
    			}
    			else
    			{
    				if (fires.get(i).get(j).getDistanceDetectable() + distanceUnitaire <= 9)
    				{
    					fires.get(i).get(j).setDistanceDetectable(fires.get(i).get(j).getDistanceDetectable()  + distanceUnitaire);
    				}
    				else
    				{
    					fires.get(i).add(new Flamme(fires.get(i).get(j).getx()+1,fires.get(i).get(j).getx(),9,fires.get(i).get(j).getDistanceDetectable()));
    				}
    			}
    			
    			
    		}
    		
    	}
    }
    
    
    
    
    
	private void addFire()
	{
		
		List<Flamme> fire = new ArrayList <Flamme>();
		
		
		double x = Math.random()*( xMax - xMin );
		double y = Math.random()*( yMax - yMin );
		
		int intensite = (int)(Math.random()*(intensiteMax-1));
		double distance = Math.random()*(distanceMax-intensite*distanceUnitaire );
		
		fire.add(new Flamme(x,y,intensite,distance));	
		fires.add(fire);

	}

}
