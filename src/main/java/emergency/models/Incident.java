package emergency.models;

import emergency.baseReferentiel.ServiceDefinitions;
import emergency.modelDto.IncidentDto;
import jakarta.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

import emergency.interfacesDefinition.*;
import org.jetbrains.annotations.NotNull;

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
    private Double latitude;

    @Column(name = "LONGITUDE")
    private Double longitude;

    @Column(name = "RADIUS")
    private Double radius;

    @Column(name = "DESCRIPTION_INCIDENT")
    private String descriptionIncident;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ADRESSE")
    private Adresse adresse;

    @ManyToOne
    @JoinColumn(name = "ID_PRIORITE")
    private Priorite priorite;

    //@JoinColumn(name = "ID_URGENCE")


    /*@JoinColumn(name = "ID_URGENCE")
    @NotNull*/

    @OneToOne
    @JoinColumn(name = "id_urgence")
    private Urgence urgence;

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

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
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

    public Urgence getUrgence() {
        return urgence;
    }

    public void setUrgence(Urgence urgence) {
        this.urgence = urgence;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }


    public IncidentDto toDto(Boolean onlyId)
    {
        IncidentDto dest = new IncidentDto();
        if(this.getAdresse()!=null)
        {
            if(onlyId==Boolean.TRUE)
            {
                try{
                    dest.setAdresseId(this.getAdresse().getId());
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                try{
                    dest.setAdresse(this.getAdresse().toDto(onlyId));
                    dest.setAdresseId(this.getAdresse().getId());
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        if(this.getPriorite()!=null)
        {
            if(onlyId==Boolean.TRUE)
            {
                try{
                    dest.setPrioriteId(this.getPriorite().getId());
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                try{
                    dest.setPriorite(this.getPriorite().toDto(onlyId));
                    dest.setPrioriteId(this.getPriorite().getId());
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        dest.setId(this.getId());
        try {
            dest.setNom(this.getNom());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dest.setDateCreation(this.getDateCreation());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dest.setDateUpdate(this.getDateUpdate());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dest.setRadius(this.getRadius());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dest.setLatitude(this.getLatitude());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dest.setLongitude(this.getLongitude());
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            dest.setDescriptionIncident(this.getDescriptionIncident());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dest;
    }

    public Incident Save(ServiceDefinitions ref, Boolean cascade) {
        Incident addr;
        try {

            if (cascade == Boolean.TRUE) {
                if (getAdresse() != null) {
                    setAdresse(getAdresse().Save(ref, cascade));
                }
            }
            addr = (Incident)ref.getIncidentService().CreateOrUpdateOrGet(this);
            return addr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
                ", radius=" + radius +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Incident incident = (Incident) o;
        return Double.compare(incident.latitude, latitude) == 0 && Double.compare(incident.longitude, longitude) == 0 && Objects.equals(nom, incident.nom) && Objects.equals(dateCreation, incident.dateCreation) && Objects.equals(dateUpdate, incident.dateUpdate) && Objects.equals(descriptionIncident, incident.descriptionIncident) && Objects.equals(adresse, incident.adresse) && Objects.equals(priorite, incident.priorite) && Objects.equals(radius, incident.radius);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, dateCreation, dateUpdate, latitude, longitude, descriptionIncident, adresse, priorite, radius);
    }
}
