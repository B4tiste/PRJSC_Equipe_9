package models;

import jakarta.persistence.*;
import java.sql.Date;
import interfacesDefinition.*;

@Entity
@Table(name = "INCIDENT")
public class Incident implements IBaseModel  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOM")
    private String nom;

    @Column(name = "DATE_CREATION")
    private Date dateCreation;

    @Column(name = "DATE_UPDATE")
    private Date dateUpdate;

    @Column(name = "LATITUDE")
    private double latitude;

    @Column(name = "LONGITUDE")
    private double longitude;

    @Column(name = "DESCRIPTION_INCIDENT")
    private String descriptionIncident;

    @ManyToOne
    @JoinColumn(name = "ID_ADRESSE")
    private Adresse adresse;

    @ManyToOne
    @JoinColumn(name = "ID_PRIORITE")
    private Priorite priorite;

    public Incident() {
    }

    public Incident(String nom, Date dateCreation, Date dateUpdate, double latitude, double longitude, String descriptionIncident, Adresse adresse, Priorite priorite) {
        this.nom = nom;
        this.dateCreation = dateCreation;
        this.dateUpdate = dateUpdate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.descriptionIncident = descriptionIncident;
        this.adresse = adresse;
        this.priorite = priorite;
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getDescriptionIncident() {
        return descriptionIncident;
    }

    public void setDescriptionIncident(String descriptionIncident) {
        this.descriptionIncident = descriptionIncident;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public Priorite getPriorite() {
        return priorite;
    }

    public void setPriorite(Priorite priorite) {
        this.priorite = priorite;
    }

    @Override
    public String toString() {
        return "Incident{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", dateCreation=" + dateCreation +
                ", dateUpdate=" + dateUpdate +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", descriptionIncident='" + descriptionIncident + '\'' +
                ", adresse=" + adresse +
                ", priorite=" + priorite +
                '}';
    }
}
