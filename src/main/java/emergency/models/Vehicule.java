package emergency.models;

import emergency.baseReferentiel.ServiceDefinitions;
import emergency.modelDto.PersonneDto;
import emergency.modelDto.VehiculeDto;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
//@DiscriminatorValue("vehicle")
@Table(name = "VEHICULE")
public class Vehicule extends RessourceComposante {

    @Column(name = "CAPACITE")
    private Long capacite;

    @Column(name = "LATITUDE")
    private Double latitude;

    @Column(name = "LONGITUDE")
    private Double longitude;

    public Vehicule() {
    }

    public Vehicule(String nom, Long capacite, Double latitude, Double longitude, Boolean isAvailable) {
        super(nom, isAvailable);
        this.capacite = capacite;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getId()
    {
        return super.getId();
    }

    public void setId(Long id)
    {
        super.setId(id);
    }

    public Long getCapacite() {
        return capacite;
    }

    public void setCapacite(Long capacite) {
        this.capacite = capacite;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
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

    public VehiculeDto toDto(Boolean onlyId)
    {
        VehiculeDto dest = new VehiculeDto();
        if(this.getPersonnes()!=null)
        {
            if(onlyId==Boolean.TRUE)
            {
                try{
                    List<Long> ids = new ArrayList<>();
                    for(Personne personne:this.getPersonnes())
                    {
                        ids.add(personne.getId());
                    }
                    dest.setPersonnesId(ids);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                try{
                    List<Personne> personnes = this.getPersonnes();
                    List<PersonneDto> personnesDto = new ArrayList<>();
                    for(Personne personne:personnes)
                    {
                        personnesDto.add(personne.toDto(onlyId));
                    }
                    dest.setPersonnes(personnesDto);
                    List<Long> ids = new ArrayList<>();
                    for(Personne personne:this.getPersonnes())
                    {
                        ids.add(personne.getId());
                    }
                    dest.setPersonnesId(ids);
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        if(this.getCapacite()!=null)
        {
            try{
                dest.setCapacite(this.getCapacite());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        if(this.getLatitude()!=null)
        {
            try{
                dest.setLatitude(this.getLatitude());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        if(this.getLongitude()!=null)
        {
            try{
                dest.setLongitude(this.getLongitude());
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        dest.setId(this.getId());
        try {
            dest.setNom(this.getNom());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dest.setAvailable(this.isAvailable());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dest;
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