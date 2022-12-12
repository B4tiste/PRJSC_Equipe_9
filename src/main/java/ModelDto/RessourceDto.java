package ModelDto;

import InterfacesDefinition.IBaseModel;
import InterfacesDefinition.IBaseModelDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

public class RessourceDto  implements IBaseModelDto {

    @NotNull
    @JsonProperty("id")
    private Long id;
    @NotNull
    @JsonProperty("nom")
    private String nom;
    @NotNull
    @JsonProperty("typeDto")
    private TypeDto typeDto;
    @NotNull
    @JsonProperty("statutDto")
    private StatutDto statutDto;
    @NotNull
    @JsonProperty("urgenceDto")
    private UrgenceDto urgenceDto;
}
