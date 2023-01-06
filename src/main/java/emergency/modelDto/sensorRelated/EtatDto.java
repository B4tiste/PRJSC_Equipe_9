package emergency.modelDto.sensorRelated;

import emergency.interfacesDefinition.IBaseModelDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

public class EtatDto implements IBaseModelDto {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("value")
    private String value;

    @NotNull
    @JsonProperty("nom")
    private String nom;

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public String getNom() {
        return nom;
    }
}