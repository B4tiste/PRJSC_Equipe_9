package Service;

import java.util.ArrayList;
import java.sql.*;
import java.util.List;

import Modele.Trouple;
import Modele.Flamme;
import Modele.Feu;

public class AccesDataSimulation extends APICommunicationBdd{

	public AccesDataSimulation()
	{
		
	}
	
	public List<Trouple> getSensors()
	{
		List<Trouple> tab  = new ArrayList<Trouple>();
		Statement stmt;
		ResultSet res;
		try {
			stmt = conn.createStatement();
			res = stmt.executeQuery("SELECT  Capteur_Donnees.id,Microcontrollers.latitude,Microcontrollers.longitude " + 
					"FROM Microcontrollers " + 
					"	JOIN Capteurs ON Capteurs.id_microcontroller = Microcontrollers.id" + 
					"	JOIN  Capteur_Donnees ON  Capteur_Donnees.id = Capteurs.id;");
			while (res.next())
			{
				tab.add(new Trouple(res.getInt("id"),res.getDouble("latitude"),res.getDouble("longitude"),0));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("tab : " + tab);
		return tab;
	}
	
	public void saveValuesSensors(List<Trouple> tab)
	{
		Statement stmt;
		for (int i = 0; i < tab.size(); i++)
		{
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("UPDATE Capteur_Donnees SET valeur=" + tab.get(i).getIntensite() + 
						"WHERE id = " + tab.get(i).getIdSensor());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	public List<Feu> getValuesFires ()
	{
		return new ArrayList <Feu>(); 
	}
	
	public void saveDataFires( List<Feu>  fires)
	{
		
	}
	
	public void createSensors ()
	{	
		System.out.println("ok");
		Statement stmt;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate("INSERT INTO Microcontrollers (id_etat,latitude,longitude,nom) VALUES (1,2,2,'0')");
			stmt.executeUpdate("INSERT INTO Microcontrollers (id_etat,latitude,longitude,nom) VALUES (1,1,3,'1')");
			stmt.executeUpdate("INSERT INTO Microcontrollers (id_etat,latitude,longitude,nom) VALUES (1,1.5,2,'2')");
			stmt.executeUpdate("INSERT INTO Microcontrollers (id_etat,latitude,longitude,nom) VALUES (1,3.5,1,'3')");
			stmt.executeUpdate("INSERT INTO Microcontrollers (id_etat,latitude,longitude,nom) VALUES (1,5,5,'4')");
			stmt.executeUpdate("INSERT INTO Microcontrollers (id_etat,latitude,longitude,nom) VALUES (1,7,3,'5')");
			stmt.executeUpdate("INSERT INTO Microcontrollers (id_etat,latitude,longitude,nom) VALUES (1,-10.5,-10,'6')");
			stmt.executeUpdate("INSERT INTO Microcontrollers (id_etat,latitude,longitude,nom) VALUES (1,-9,-10,'7')");
			stmt.executeUpdate("INSERT INTO Microcontrollers (id_etat,latitude,longitude,nom) VALUES (1,-9,-8,'8')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for ( int i = 0; i < 8; i++)
		{
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("INSERT INTO Capteurs (identifier,id_microcontroller,id_capteur_type) "
									+ "VALUES ("+ (i+1) + "," + (i+1) + ","  +"1)");
				stmt.executeUpdate("INSERT INTO Capteur_Donnees (identifier,id_capteur,valeur) VALUES ("+ (i+1) + ","+ (i+1)  + ",0)");
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
