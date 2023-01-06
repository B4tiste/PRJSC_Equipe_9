package emergency.models.sensorRelated;


import emergency.baseReferentiel.ServiceDefinitions;
import emergency.models.Adresse;
import jakarta.persistence.*;
import emergency.interfacesDefinition.*;

import java.util.Objects;


@Entity
@Table(name = "CAPTEUR_TYPE")
public class CapteurType implements IBaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TYPE")
    private String type;


    @Column(name = "VALEUR")
    private int valeur;


    public CapteurType() {
    }

    public CapteurType(String type, int valeur) {
        this.type = type;
        this.valeur = valeur;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getValeur() {
        return valeur;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public CapteurType Save(ServiceDefinitions ref, Boolean cascade) {
        CapteurType etat;
        try {
            etat = (CapteurType)ref.getTypeCapteurService().CreateOrUpdateOrGet(this);
            if (cascade == Boolean.TRUE) {

            }
            return etat;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CapteurType that = (CapteurType) o;
        return Objects.equals(type, that.type);
    }

}
