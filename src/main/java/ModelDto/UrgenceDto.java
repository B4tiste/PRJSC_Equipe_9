package ModelDto;


import InterfacesDefinition.IBaseModel;
import InterfacesDefinition.IBaseModelDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import java.sql.Date;


public class UrgenceDto implements IBaseModelDto {

    @NotNull
    @JsonProperty("id")
    private Long id;
    @NotNull
    @JsonProperty("dateCreation")
    private Date dateCreation;

    @NotNull
    @JsonProperty("dateUpdate")
    private Date dateUpdate;

    @NotNull
    @JsonProperty("titre")
    private String titre;

    @NotNull
    @JsonProperty("incidentDto")
    private IncidentDto incidentDto;

    @NotNull
    @JsonProperty("typeDto")
    private TypeDto typeDto;

    @NotNull
    @JsonProperty("statutDto")
    private StatutDto statutDto;
}
