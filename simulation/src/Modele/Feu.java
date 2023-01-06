package Modele;

import java.util.List;

public class Feu {

	private List<Flamme> flammes;
	private int idFeu;
	
	public Feu(List<Flamme> flammes,int idFeu)
	{
		this.flammes = flammes;
		this.idFeu = idFeu;
	}
	
	public int getIdFeu()
	{
		return idFeu;
	}
	
	public List<Flamme> getFlammes()
	{
		return flammes;
	}
	
	public void addFlamme(Flamme f)
	{
		flammes.add(f);
	}
	
	public Flamme getFlamme(int i)
	{
		return flammes.get(i);
	}
	
	public int sizeFeu()
	{
		return flammes.size();
	}
	
	public String toString()
	{
		   return ""+this.flammes;
	}
	
	public void removeFeur(int i)
	{
		flammes.remove(i);
	}
}
