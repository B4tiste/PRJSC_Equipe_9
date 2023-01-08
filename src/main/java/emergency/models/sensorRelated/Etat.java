package emergency.models.sensorRelated;


import emergency.baseReferentiel.ServiceDefinitions;
import emergency.modelDto.sensorRelated.EtatDto;
import jakarta.persistence.*;
import emergency.interfacesDefinition.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "ETAT")
public class Etat implements IBaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "VALEUR")
    private int valeur;

    @Column(name = "NOM")
    private String nom;

    @OneToMany(mappedBy = "etat")
    private List<Microcontroller> microcontrollers;

    public Etat() {
    }

    public Etat(String nom, int valeur) {
        this.valeur = valeur;
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public List<Microcontroller> getMicrocontrollers() {
        return microcontrollers;
    }

    public void setMicrocontrollers(List<Microcontroller> microcontrollers) {
        this.microcontrollers = microcontrollers;
    }

    public Etat Save(ServiceDefinitions ref, Boolean cascade) {
        Etat etat;
        try {
            etat = (Etat)ref.getEtatService().CreateOrUpdateOrGet(this);
            if (cascade == Boolean.TRUE) {

            }
            return etat;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public EtatDto toDto(Boolean onlyId)
    {
        EtatDto dest = new EtatDto();
        dest.setId(this.getId());
        try {
            dest.setNom(this.getNom());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dest.setValue(this.getValeur());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Etat etat = (Etat) o;
        return Objects.equals(valeur, etat.valeur) && Objects.equals(nom, etat.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valeur, microcontrollers);
    }
}
