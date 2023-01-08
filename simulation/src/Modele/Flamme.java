package Modele;

public class Flamme {
	
	private int idFeuAppartient;
	private int idFlamme;
	private double x;
	private double y;
	private int intensite;
	private double distanceDetectable;
	
	
	private int rayon_max = 9;
	
	/**
	 * @param x
	 * @param y
	 * @param intensite
	 */
	public Flamme (int idFeuAppartient,double x, double y, int intensite, double distanceDetectable, int idFlamme)
	{
		this.idFeuAppartient = idFeuAppartient;
		this.x = x;
		this.y = y;
		this.intensite = intensite;
		this.distanceDetectable = distanceDetectable;
		this.idFlamme = idFlamme;
	}
	
	/**
	 * @return le x du trouple coordonée, intensité
	 */
	public double getx()
	{
		return this.x;
	}
	
	/**
	 * @return le y du trouple coordonée, intensité
	 */
	public double gety()
	{
		return this.y;
	}
	
	/**
	 * @return l'intensité du trouple coordonée, intensité
	 */
	public int getIntensite()
	{
		return this.intensite;
	}
	
	/**
	 * @return l'intensité du trouple coordonée, intensité
	 */
	public double getDistanceDetectable()
	{
		return this.distanceDetectable;
	}
	
	/**
	 * @return renvoie le rayon du cercle pour l'intensité donnée
	 */
	public double getRayon()
	{
		return rayon_max - this.intensite;
	}
	
	public void setIntensite(int intensite)
	{
		this.intensite = intensite;
	}
	
	public void setDistanceDetectable(double distanceDetectable)
	{
		this.distanceDetectable = distanceDetectable;
	}
	
	/**
	 * @brief permet d'afficher l'objet
	 */
	public String toString() {
		   return "(" + this.x+
			  "," + this.y +
			  "," + this.intensite +
			  "," + this.distanceDetectable
			  +")";
		}
	
	public int getIdFlamme()
	{
		return this.idFlamme;
	}
	
	public int getIdFeuAppartient()
	{
		return idFeuAppartient;
	}

}
