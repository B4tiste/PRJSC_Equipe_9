package Controler;

import java.util.List;


import Modele.*;
import simulation.ThreadChangeValueSensor;
import simulation.ThreadGenerateFire;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class CalculMain {
	
	
	AtomicBoolean enFonction;
	ConcurrentLinkedDeque<Integer> cld = new ConcurrentLinkedDeque<Integer>();
	
	public CalculMain ()
	{
		enFonction  = new AtomicBoolean(true);

		
		//calValSensor (tab,fires);
		
		ArrayBlockingQueue<List<List<Flamme>>>  queue = new ArrayBlockingQueue<>(1);
		
		ThreadGenerateFire threadGenerate = new ThreadGenerateFire(queue,enFonction);
		threadGenerate.start();
		
		ThreadChangeValueSensor threadSensor = new ThreadChangeValueSensor(queue,enFonction);
		threadSensor.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		enFonction.set(false) ;
		System.out.println("-------------------------------------------");

	}
	


	
}
