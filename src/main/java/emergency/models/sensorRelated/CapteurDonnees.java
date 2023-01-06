package emergency.models.sensorRelated;


import emergency.baseReferentiel.ServiceDefinitions;
import emergency.interfacesDefinition.IBaseModel;
import emergency.models.Adresse;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

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

    @Column(name = "date_creation")
    private Date dateCreation;

    @Column(name = "date_update")
    private Date dateUpdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CAPTEUR")
    private Capteur capteur;



    public CapteurDonnees() {
    }

    public CapteurDonnees(String identifier, String valeur, Capteur capteur, Date dateCreation, Date dateUpdate) {
        this.identifier = identifier;
        this.valeur = valeur;
        this.capteur = capteur;
        this.dateCreation = dateCreation;
        this.dateUpdate = dateUpdate;
    }

    public CapteurDonnees Save(ServiceDefinitions ref, Boolean cascade) {
        CapteurDonnees etat;
        try {
            etat = (CapteurDonnees)ref.getCapteurDonneesService().CreateOrUpdateOrGet(this);
            if (cascade == Boolean.TRUE) {

            }
            return etat;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CapteurDonnees that = (CapteurDonnees) o;
        return Objects.equals(identifier, that.identifier) &&
                Objects.equals(valeur, that.valeur) &&
                Objects.equals(capteur, that.capteur);
    }
}
