package models;

import interfacesDefinition.IBaseModel;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "TYPE_URGENCE")
public class TypeUrgence implements IBaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOM")
    private String nom;

    @Column(name = "VALEUR")
    private int valeur;

    @OneToMany(mappedBy = "type")
    private Set<Urgence> urgences;

    public TypeUrgence() {
    }

    public TypeUrgence(String nom, int valeur) {
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

    public Set<Urgence> getUrgences() {
        return urgences;
    }

    public void setUrgences(Set<Urgence> urgences) {
        this.urgences = urgences;
    }

    @Override
    public String toString() {
        return "TypeUrgence{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", valeur=" + valeur +
                '}';
    }
}