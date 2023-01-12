package emergency.Algo;

public class Trouple {
    public double x;
    public double y;
    public int idSensor;
    public int intensite;
    private double distanceUnitaire = Trouple.distanceUnitaireBase;
    private double rayon_max = 9 * Trouple.distanceUnitaireBase;

    public static double distanceUnitaireBase = 1.0;

    public static void calcDistanceUnitaire(double meters)
    {
        Trouple.distanceUnitaireBase = meters / 9.0;
    }

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

    public Trouple()
    {

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

    public double getCustomRayon(Double dis)
    {
        return this.intensite / 9.0 * dis;
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