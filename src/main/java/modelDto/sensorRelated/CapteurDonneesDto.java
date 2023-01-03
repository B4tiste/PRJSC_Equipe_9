package modelDto.sensorRelated;

import interfacesDefinition.IBaseModelDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

public class CapteurDonneesDto  implements IBaseModelDto {
    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("identifier")
    private String identifier;

    @NotNull
    @JsonProperty("valeur")
    private String valeur;

    @NotNull
    @JsonProperty("capteur")
    private Long capteur;

    public Long getId() {
        return id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getValeur() {
        return valeur;
    }

    public Long getCapteur() {
        return capteur;
    }
}