package models;

import jakarta.persistence.*;
import interfacesDefinition.*;

import java.util.Set;

@Entity
@Table(name = "STATUT")
public class Statut implements IBaseModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOM")
    private String nom;

    @Column(name = "VALEUR")
    private int valeur;


    @OneToMany(mappedBy = "statut")
    private Set<Urgence> ressources;


    public Statut() {
    }

    public Statut(String nom, int valeur) {
        this.nom = nom;
        this.valeur = valeur;
    }
    @Override
    public Long getId() {
        return id;
    }
    @Override
    public void setId(Long id) {
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

    public Set<Urgence> getRessources() {
        return ressources;
    }

    public void setRessources(Set<Urgence> ressources) {
        this.ressources = ressources;
    }

    @Override
    public String toString() {
        return "Statut{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", valeur=" + valeur +
                '}';
    }
}
