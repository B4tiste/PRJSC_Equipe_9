package emergency.modelDto;

import emergency.interfacesDefinition.IBaseModelDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

public class AdresseDto implements IBaseModelDto {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("rue")
    private String rue;

    @NotNull
    @JsonProperty("ville")
    private String ville;

    @NotNull
    @JsonProperty("etat")
    private String etat;

    @NotNull
    @JsonProperty("codePostal")
    private String codePostal;

    @NotNull
    @JsonProperty("pays")
    private String pays;

    public Long getId() {
        return id;
    }

    public String getRueAdresse() {
        return rue;
    }

    public String getVilleAdresse() {
        return ville;
    }

    public String getEtatAdresse() {
        return etat;
    }

    public String getCodePostalAdresse() {
        return codePostal;
    }

    public String getPaysAdresse() {
        return pays;
    }
}
