package emergency.models;

import emergency.baseReferentiel.ServiceDefinitions;
import emergency.modelDto.StatutDto;
import jakarta.persistence.*;
import emergency.interfacesDefinition.*;

import java.util.Objects;
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

    public Statut Save(ServiceDefinitions ref, Boolean cascade)
    {
        Statut addr;
        try{
            addr = (Statut) ref.getStatutService().CreateOrUpdateOrGet(this);
            return addr;
        }catch (Exception e)
        {
            e.printStackTrace();

        }
        return null;
    }

    public StatutDto toDto(Boolean onlyId)
    {
        StatutDto dest = new StatutDto();

        dest.setId(this.getId());
        try {
            dest.setNom(this.getNom());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dest.setValeur(this.getValeur());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dest;
    }

    @Override
    public String toString() {
        return "Statut{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", valeur=" + valeur +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Statut statut = (Statut) o;
        return valeur == statut.valeur && Objects.equals(nom, statut.nom) && Objects.equals(ressources, statut.ressources);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, valeur, ressources);
    }
}
