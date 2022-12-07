package Models;

import InterfacesDefinition.*;
import jakarta.persistence.*;



@Entity
@Table(name = "CAPTEUR")
public class Capteur implements IBaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "LATITUDE")
    private Float latitude;

    @Column(name = "LONGITUDE")
    private Float longitude;

    @Column(name = "IDENTIFIER")
    private String identifier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAPTEUR_TYPE")
    private CapteurType sensorType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAPTEUR_ETAT")
    private CapteurEtat sensorState;

    public Capteur() {
    }

    public Capteur(Float latitude, Float longitude, String identifier, CapteurType sensorType, CapteurEtat sensorState) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.identifier = identifier;
        this.sensorType = sensorType;
        this.sensorState = sensorState;
    }

    @Override
    public Long getId() {
        return id;
    }
    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public CapteurType getSensorType() {
        return sensorType;
    }

    public void setSensorType(CapteurType sensorType) {
        this.sensorType = sensorType;
    }

    public CapteurEtat getSensorState() {
        return sensorState;
    }

    public void setSensorState(CapteurEtat sensorState) {
        this.sensorState = sensorState;
    }
}