package emergency.models.sensorRelated;


import emergency.interfacesDefinition.IBaseModel;
import jakarta.persistence.*;

@Entity
@Table(name = "CAPTEUR_DONNEES")
public class CapteurDonnees implements IBaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "IDENTIFIER")
    private String identifier;

    @Column(name = "VALEUR")
    private String valeur;

    @ManyToOne
    @JoinColumn(name = "ID_CAPTEUR")
    private Capteur capteur;

    public CapteurDonnees() {
    }

    public CapteurDonnees(String identifier, String valeur, Capteur capteur) {
        this.identifier = identifier;
        this.valeur = valeur;
        this.capteur = capteur;
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

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public Capteur getCapteur() {
        return capteur;
    }

    public void setCapteur(Capteur capteur) {
        this.capteur = capteur;
    }
}
