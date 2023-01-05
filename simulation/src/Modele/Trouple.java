package Modele;
/*test*/ 
public class Trouple {
	private double x;
	private double y;
	private int intensite;
	private double distanceUnitaire = 1;
	private double rayon_max = 9 * distanceUnitaire;
	
	/**
	 * @param x
	 * @param y
	 * @param intensite
	 */
	public Trouple(double x, double y, int intensite)
	{
		this.x = x;
		this.y = y;
		this.intensite = intensite;
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
	 * @return renvoie le rayon du cercle pour l'intensité donnée
	 */
	public double getRayon()
	{
		return rayon_max - this.intensite * distanceUnitaire;
	}
	
	public void setIntensite(int intensite)
	{
		this.intensite = intensite;
	}
	
	/**
	 * @brief permet d'afficher l'objet
	 */
	public String toString() {
		   return "(" + this.x+
			  "," + this.y +
			  "," + this.intensite
			  +")";
		}
	
}
