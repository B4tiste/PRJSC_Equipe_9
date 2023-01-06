package emergency.models;

import emergency.baseReferentiel.ServiceDefinitions;
import jakarta.persistence.*;

import java.util.Objects;

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


    public Vehicule Save(ServiceDefinitions ref, Boolean cascade) {
        Vehicule addr;
        try {
            addr = (Vehicule)ref.getVehicleService().CreateOrUpdateOrGet(this);
            if (cascade == Boolean.TRUE) {
                //à faire
            }
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
        if (!super.equals(o)) return false;
        Vehicule vehicule = (Vehicule) o;
        return Objects.equals(capacite, vehicule.capacite) && Objects.equals(latitude, vehicule.latitude) && Objects.equals(longitude, vehicule.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), capacite, latitude, longitude);
    }
}