package emergency.models;

import jakarta.persistence.*;

@Entity
@Table(name = "VEHICULE")
public class Vehicule extends RessourceComposante {

    @Column(name = "CAPACITE")
    private Long capacite;

    @Column(name = "LATITUDE")
    private Long latitude;

    @Column(name = "LONGITUDE")
    private Long longitude;

    public Vehicule() {
    }

    public Vehicule(String nom, Long capacite, Long latitude, Long longitude, Boolean isAvailable) {
        super(nom, isAvailable);
        this.capacite = capacite;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    @Override
    public Long getId()
    {
        return this.getId();
    }
    @Override
    public void setId(Long id)
    {
        this.setId(id);
    }

    public Long getCapacite() {
        return capacite;
    }

    public void setCapacite(Long capacite) {
        this.capacite = capacite;
    }

    public Long getLatitude() {
        return latitude;
    }

    public void setLatitude(Long latitude) {
        this.latitude = latitude;
    }

    public Long getLongitude() {
        return longitude;
    }

    public void setLongitude(Long longitude) {
        this.longitude = longitude;
    }
}