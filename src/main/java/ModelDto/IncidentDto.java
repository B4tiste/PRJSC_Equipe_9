package ModelDto;

import InterfacesDefinition.IBaseModel;
import InterfacesDefinition.IBaseModelDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import java.sql.Date;


public class IncidentDto  implements IBaseModelDto {

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
    private double latitude;

    @NotNull
    @JsonProperty("longitude")
    private double longitude;

    @NotNull
    @JsonProperty("descriptionIncident")
    private String descriptionIncident;

    @NotNull
    @JsonProperty("adresseDto")
    private Long adresseDto;

    @NotNull
    @JsonProperty("prioriteDto")
    private Long prioriteDto;

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

    public Long getAdresseDto() {
        return adresseDto;
    }

    public Long getPrioriteDto() {
        return prioriteDto;
    }
}
