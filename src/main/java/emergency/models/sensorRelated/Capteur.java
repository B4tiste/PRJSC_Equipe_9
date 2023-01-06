package emergency.models.sensorRelated;

import emergency.baseReferentiel.ServiceDefinitions;
import emergency.interfacesDefinition.*;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;


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

    @OneToMany(mappedBy = "capteur", cascade = CascadeType.ALL)
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


    public Capteur Save(ServiceDefinitions ref, Boolean cascade) {
        Capteur addr;
        try {

            if (cascade == Boolean.TRUE) {

                List<CapteurDonnees> capteurDonnees = getCapteurDonnees();
                for (int c = 0; c < capteurDonnees.size(); c++) {
                    CapteurDonnees capteurDonnee = capteurDonnees.get(c);
                    CapteurDonnees savedCapteurDonnee = capteurDonnee.Save(ref, cascade);
                    if (savedCapteurDonnee != null) {
                        capteurDonnees.set(c, savedCapteurDonnee);
                    }
                    setCapteurDonnees(capteurDonnees);
                }
            }
            addr = (Capteur)ref.getCapteurService().CreateOrUpdateOrGet(this);
            return addr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Capteur capteur = (Capteur) o;
        return
                Objects.equals(identifier, capteur.identifier) &&
                Objects.equals(microcontroller, capteur.microcontroller) &&
                Objects.equals(capteurDonnees, capteur.capteurDonnees) &&
                Objects.equals(capteurType, capteur.capteurType);
    }
}
