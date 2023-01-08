package Service;

import java.util.ArrayList;
import java.util.List;

import Modele.*;

public class AccesDataEmergency extends APICommunicationBdd{
	
	public void APICommunicationBdd()
	{
		
	}
	
	public List<Trouple> getSensorValues ()
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
		

		return tab;
	}
	
	
	public void saveValuesFires(List<Trouple> fires)
	{

		
	}

}
