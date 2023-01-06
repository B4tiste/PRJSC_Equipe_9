package emergency.models;

import emergency.baseReferentiel.ServiceDefinitions;
import jakarta.persistence.*;
import emergency.interfacesDefinition.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "PRIORITE")
public class Priorite implements IBaseModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOM")
    private String nom;

    @Column(name = "VALEUR")
    private int valeur;

    @OneToMany(mappedBy = "priorite")
    private Set<Incident> incidents;

    public Priorite() {
    }

    public Priorite(String nom, int valeur) {
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

    public Set<Incident> getIncidents() {
        return incidents;
    }

    public void setIncidents(Set<Incident> incidents) {
        this.incidents = incidents;
    }

    public Priorite Save(ServiceDefinitions ref, Boolean cascade)
    {
        Priorite addr;
        try{
            addr = (Priorite)ref.getPrioriteService().CreateOrUpdateOrGet(this);
            return addr;
        }catch (Exception e)
        {
            e.printStackTrace();

        }
        return null;
    }

    @Override
    public String toString() {
        return "Priorite{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", valeur=" + valeur +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Priorite priorite = (Priorite) o;
        return valeur == priorite.valeur && Objects.equals(nom, priorite.nom) && Objects.equals(incidents, priorite.incidents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, valeur, incidents);
    }
}

