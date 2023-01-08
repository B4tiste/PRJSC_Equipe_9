package emergency.modelDto;

import emergency.baseReferentiel.ReferentielDefinitions;
import emergency.baseReferentiel.ServiceDefinitions;
import emergency.interfacesDefinition.IBaseModelDto;
import emergency.modelDto.GBaseDto;
import emergency.models.Adresse;
import emergency.models.Incident;
import com.fasterxml.jackson.annotation.JsonProperty;
import emergency.models.Priorite;
import org.jetbrains.annotations.NotNull;

import java.sql.Date;


public class IncidentDto extends GBaseDto implements IBaseModelDto {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("nom")
    private String nom;

    @NotNull
    @JsonProperty("dateCreation")
    private Date dateCreation;

    @NotNull
    @JsonProperty("dateUpdate")
    private Date dateUpdate;

    @NotNull
    @JsonProperty("latitude")
    private Double latitude;

    @NotNull
    @JsonProperty("longitude")
    private Double longitude;

    @NotNull
    @JsonProperty("radius")
    private Double radius;

    @NotNull
    @JsonProperty("descriptionIncident")
    private String descriptionIncident;

    @NotNull
    @JsonProperty("adresse")
    private AdresseDto adresse;

    @JsonProperty("adresseId")
    private Long adresseId;

    @NotNull
    @JsonProperty("priorite")
    private PrioriteDto priorite;

    @JsonProperty("prioriteId")
    private Long prioriteId;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setDescriptionIncident(String descriptionIncident) {
        this.descriptionIncident = descriptionIncident;
    }

    public void setAdresse(AdresseDto adresse) {
        this.adresse = adresse;
    }

    public void setAdresseId(Long adresseId) {
        this.adresseId = adresseId;
    }

    public void setPriorite(PrioriteDto priorite) {
        this.priorite = priorite;
    }

    public void setPrioriteId(Long prioriteId) {
        this.prioriteId = prioriteId;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getDescriptionIncident() {
        return descriptionIncident;
    }

    public AdresseDto getAdresse() {
        return adresse;
    }

    public PrioriteDto getPriorite() {
        return priorite;
    }

    public Long getAdresseId() {
        return adresseId;
    }

    public Long getPrioriteId() {
        return prioriteId;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public Incident toModel()
    {
        Incident model = new Incident();
        if(this.getId()!=null)
        {
            model.setId(this.getId());
        }
        model.setNom(this.getNom());
        model.setRadius(this.getRadius());
        model.setDateCreation(this.getDateCreation());
        model.setDateUpdate(this.getDateUpdate());
        model.setLatitude(this.getLatitude());
        model.setLongitude(this.getLongitude());
        model.setDescriptionIncident(this.getDescriptionIncident());
        if(this.getAdresse()!=null)
        {
            try {
                model.setAdresse((Adresse) this.getAdresse().toModel());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(this.getAdresseId()!=null)
        {
            try {
                model.setAdresse((Adresse)this.getServices().getAdresseService().getById(this.getAdresseId()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(this.getPriorite()!=null)
        {
            try {
                model.setPriorite((Priorite) this.getPriorite().toModel());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(this.getPrioriteId()!=null)
        {
            try {
                model.setPriorite(ReferentielDefinitions.getPriorite(this.getPrioriteId()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return model;
    }
}