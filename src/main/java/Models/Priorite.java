package Models;

import jakarta.persistence.*;

@Entity
@Table(name = "PRIORITE")
public class Priorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NOM")
    private String nom;

    @Column(name = "VALEUR")
    private int valeur;

    public Priorite() {
    }

    public Priorite(String nom, int valeur) {
        this.nom = nom;
        this.valeur = valeur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    @Override
    public String toString() {
        return "Priorite{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", valeur=" + valeur +
                '}';
    }
}

