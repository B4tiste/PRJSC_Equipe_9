package Models;

import jakarta.persistence.*;
import InterfacesDefinition.*;


@Entity
@Table(name = "RESSOURCECOMPOSANTE")
public class RessourceComposante implements IBaseModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOM")
    private String nom;

    @Column(name = "IS_AVAILABLE")
    private boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "ID_RESSOURCE")
    private Ressource ressource;

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
        return ressource;
    }

    public void setRessource(Ressource ressource) {
        this.ressource = ressource;
    }

    @Override
    public String toString() {
        return "RessourceComposante{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", isAvailable=" + isAvailable +
                ", ressource=" + ressource +
                '}';
    }
}