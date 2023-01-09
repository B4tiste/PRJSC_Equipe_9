package emergency.Algo;

public class Trouple {
    private double x;
    private double y;
    private int idSensor;
    private int intensite;
    private double distanceUnitaire = 1;
    private double rayon_max = 9 * distanceUnitaire;

    /**
     * @param x
     * @param y
     * @param intensite
     */
    public Trouple(int idSensor, double x, double y, int intensite)
    {
        this.x = x;
        this.y = y;
        this.intensite = intensite;
        this.idSensor = idSensor;
    }

    /**
     * @return le x du trouple coordonÃ©e, intensitÃ©
     */
    public double getx()
    {
        return this.x;
    }

    /**
     * @return le y du trouple coordonÃ©e, intensitÃ©
     */
    public double gety()
    {
        return this.y;
    }

    /**
     * @return l'intensitÃ© du trouple coordonÃ©e, intensitÃ©
     */
    public int getIntensite()
    {
        return this.intensite;
    }

    /**
     * @return renvoie le rayon du cercle pour l'intensitÃ© donnÃ©e
     */
    public double getRayon()
    {
        return rayon_max - this.intensite * distanceUnitaire;
    }

    public void setIntensite(int intensite)
    {
        this.intensite = intensite;
    }

    public int getIdSensor()
    {
        return this.idSensor;
    }

    /**
     * @brief permet d'afficher l'objet
     */
    public String toString() {
        return "(" +
                "," + this.idSensor +
                "," + this.x +
                "," + this.y +
                "," + this.intensite
                +")";
    }

}