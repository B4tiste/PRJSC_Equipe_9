package Models;

import jakarta.persistence.*;

@Entity
@Table(name = "RESSOURCE")
public class Ressource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NOM")
    private String nom;

    @ManyToOne
    @JoinColumn(name = "ID_TYPE")
    private Type type;

    @ManyToOne
    @JoinColumn(name = "ID_STATUT")
    private Statut statut;

    @ManyToOne
    @JoinColumn(name = "ID_URGENCE")
    private Urgence urgence;

    public Ressource() {
    }

    public Ressource(String nom, Type type, Statut statut, Urgence urgence) {
        this.nom = nom;
        this.type = type;
        this.statut = statut;
        this.urgence = urgence;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public Urgence getUrgence() {
        return urgence;
    }

    public void setUrgence(Urgence urgence) {
        this.urgence = urgence;
    }

    @Override
    public String toString() {
        return "Ressource{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", type=" + type +
                ", statut=" + statut +
                ", urgence=" + urgence +
                '}';
    }
}
