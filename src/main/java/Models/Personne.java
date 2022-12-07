package Models;

import jakarta.persistence.*;

@Entity
@Table(name = "PERSONNE")
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NOM")
    private String nom;

    @Column(name = "PRENOM")
    private String prenom;

    @ManyToOne
    @JoinColumn(name = "ID_ROLE")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "ID_RESSOURCECOMPOSANTE")
    private RessourceComposante ressourceComposante;

    public Personne() {
    }

    public Personne(String nom, String prenom, Role role, RessourceComposante ressourceComposante) {
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.ressourceComposante = ressourceComposante;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}