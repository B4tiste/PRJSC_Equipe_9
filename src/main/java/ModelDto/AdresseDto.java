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
    private int codePostalAdresse;

    @NotNull
    @JsonProperty("paysAdresse")
    private String paysAdresse;
}
