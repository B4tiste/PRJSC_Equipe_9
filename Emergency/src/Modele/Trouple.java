package Modele;

public class Trouple {
	private double x;
	private double y;
	private int intensite;
	private int rayon_max = 9;
	
	public Trouple(double x, double y, int intensite)
	{
		this.x = x;
		this.y = y;
		this.intensite = intensite;
	}
	
	public double getx()
	{
		return this.x;
	}
	
	public double gety()
	{
		return this.y;
	}
	
	public int getIntensite()
	{
		return this.intensite;
	}
	
	public double getRayon()
	{
		return rayon_max - this.intensite;
	}
	
	public String toString() {
		   return "(" + this.x+
			  "," + this.y +
			  "," + this.intensite
			  +")";
		}
	
}
