package Service;

import java.sql.*;

public class APICommunicationBdd {
	
	Connection conn;
	
	
	public APICommunicationBdd()
	{
		try {
			conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dbSimulation","postgres","postgres");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("ok connection BDD");
		
		
	      
	      
	}

}
