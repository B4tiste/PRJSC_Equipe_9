package ModelDto;

import InterfacesDefinition.IBaseModel;
import InterfacesDefinition.IBaseModelDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

public class AdresseDto implements IBaseModelDto {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("rueAdresse")
    private String rueAdresse;

    @NotNull
    @JsonProperty("villeAdresse")
    private String villeAdresse;

    @NotNull
    @JsonProperty("etatAdresse")
    private String etatAdresse;

    @NotNull
    @JsonProperty("codePostalAdresse")
    private String codePostalAdresse;

    @NotNull
    @JsonProperty("paysAdresse")
    private String paysAdresse;

    public Long getId() {
        return id;
    }

    public String getRueAdresse() {
        return rueAdresse;
    }

    public String getVilleAdresse() {
        return villeAdresse;
    }

    public String getEtatAdresse() {
        return etatAdresse;
    }

    public String getCodePostalAdresse() {
        return codePostalAdresse;
    }

    public String getPaysAdresse() {
        return paysAdresse;
    }
}
