package models.sensorRelated;

import interfacesDefinition.*;

import jakarta.persistence.*;

import java.util.List;


@Entity
@Table(name = "CAPTEUR")
public class Capteur implements IBaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "IDENTIFIER")
    private String identifier;

    @ManyToOne
    @JoinColumn(name = "ID_MICROCONTROLLER")
    private Microcontroller microcontroller;

    @OneToMany(mappedBy = "capteur")
    private List<CapteurDonnees> capteurDonnees;

    @ManyToOne
    @JoinColumn(name = "ID_CAPTEUR_TYPE")
    private CapteurType capteurType;

    public Capteur() {
    }

    public Capteur(String identifier, Microcontroller microcontroller, List<CapteurDonnees> capteurDonnees, CapteurType capteurType) {
        this.identifier = identifier;
        this.microcontroller = microcontroller;
        this.capteurDonnees = capteurDonnees;
        this.capteurType = capteurType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public Microcontroller getMicrocontroller() {
        return microcontroller;
    }

    public void setMicrocontroller(Microcontroller microcontroller) {
        this.microcontroller = microcontroller;
    }

    public List<CapteurDonnees> getCapteurDonnees() {
        return capteurDonnees;
    }

    public void setCapteurDonnees(List<CapteurDonnees> capteurDonnees) {
        this.capteurDonnees = capteurDonnees;
    }

    public CapteurType getCapteurType() {
        return capteurType;
    }

    public void setCapteurType(CapteurType capteurType) {
        this.capteurType = capteurType;
    }
}
