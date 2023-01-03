package emergency.models;

import jakarta.persistence.*;
import emergency.interfacesDefinition.*;


@Entity
@Table(name = "RESSOURCE")
public class Ressource implements IBaseModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOM")
    private String nom;

    @ManyToOne
    @JoinColumn(name = "ID_TYPE")
    private TypeRessource type;

    @ManyToOne
    @JoinColumn(name = "ID_STATUT")
    private Statut statut;

    @OneToOne
    @JoinColumn(name = "ID_RESSOURCECOMPOSANTE")
    private RessourceComposante ressourceComposante;

    public Ressource() {
    }

    public Ressource(String nom, TypeRessource type, Statut statut, RessourceComposante ressourceComposante) {
        this.nom = nom;
        this.type = type;
        this.statut = statut;
        this.ressourceComposante = ressourceComposante;
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

    public TypeRessource getType() {
        return type;
    }

    public void setType(TypeRessource type) {
        this.type = type;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public RessourceComposante getRessourceComposante() {
        return ressourceComposante;
    }

    public void setRessourceComposante(RessourceComposante ressourceComposante) {
        this.ressourceComposante = ressourceComposante;
    }

    @Override
    public String toString() {
        return "TypeRessource{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", type=" + type +
                ", statut=" + statut +
                ", ressourceComposante=" + ressourceComposante +
                '}';
    }
}
