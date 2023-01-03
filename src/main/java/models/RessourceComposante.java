package models;

import jakarta.persistence.*;
import interfacesDefinition.*;
import models.sensorRelated.Capteur;

import java.util.List;

import static jakarta.persistence.FetchType.LAZY;


@Entity
@Table(name = "RESSOURCECOMPOSANTE")
public class RessourceComposante implements IBaseModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "NOM")
    private String nom;

    @Column(name = "IS_AVAILABLE")
    private boolean isAvailable;

    @OneToMany(mappedBy = "ressourceComposante", cascade = CascadeType.ALL)
    private List<Personne> personnes;

    @OneToOne
    @JoinColumn(name = "ID_RESSOURCE")
    private Ressource ressource;

    @ManyToOne
    @JoinColumn(name = "ID_CENTRE")
    private Centre centre;


    public RessourceComposante() {
    }

    public RessourceComposante(String nom, boolean isAvailable) {
        this.nom = nom;
        this.isAvailable = isAvailable;
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

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Ressource getRessource() {
        return this.ressource;
    }

    public void setRessource(Ressource ressource) {
        this.ressource = ressource;
    }


    public List<Personne> getPersonnes() {
        return personnes;
    }

    public void setPersonnes(List<Personne> personnes) {
        this.personnes = personnes;
    }

    @Override
    public String toString() {
        return "RessourceComposante{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }


}