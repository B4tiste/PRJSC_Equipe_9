package ModelDto.SensorRelated;

import InterfacesDefinition.IBaseModelDto;
import Models.SensorRelated.Capteur;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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
    private Capteur capteur;
}