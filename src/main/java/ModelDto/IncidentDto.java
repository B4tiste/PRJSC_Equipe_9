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
    private AdresseDto adresseDto;

    @NotNull
    @JsonProperty("prioriteDto")
    private PrioriteDto prioriteDto;

}
