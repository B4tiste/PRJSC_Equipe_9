package emergency.models.sensorRelated;


import jakarta.persistence.*;
import emergency.interfacesDefinition.*;

import java.util.List;

@Entity
@Table(name = "ETAT")
public class Etat implements IBaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "VALEUR")
    private String valeur;

    @OneToMany(mappedBy = "etat")
    private List<Microcontroller> microcontrollers;

    public Etat() {
    }

    public Etat(String valeur, List<Microcontroller> microcontrollers) {
        this.valeur = valeur;
        this.microcontrollers = microcontrollers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public List<Microcontroller> getMicrocontrollers() {
        return microcontrollers;
    }

    public void setMicrocontrollers(List<Microcontroller> microcontrollers) {
        this.microcontrollers = microcontrollers;
    }
}
