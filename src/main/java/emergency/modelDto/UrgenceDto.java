package emergency.modelDto;


import emergency.interfacesDefinition.IBaseModelDto;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Long incident;

    @NotNull
    @JsonProperty("typeDto")
    private Long type;

    @NotNull
    @JsonProperty("statutDto")
    private Long statut;

    @JsonProperty("ressourceDto")
    private Long ressourceDto;

    public Long getId() {
        return id;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public String getTitre() {
        return titre;
    }

    public Long getIncidentDto() {
        return incident;
    }

    public Long getTypeRessourceDto() {
        return type;
    }

    public Long getStatutDto() {
        return statut;
    }

    public Long getRessourceDto() { return ressourceDto; }
}
