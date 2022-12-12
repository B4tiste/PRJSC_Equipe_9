package ModelDto;

import InterfacesDefinition.IBaseModel;
import InterfacesDefinition.IBaseModelDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

public class PrioriteDto  implements IBaseModelDto {

    @NotNull
    @JsonProperty("id")
    private Long id;
    @NotNull
    @JsonProperty("nom")
    private String nom;
    @NotNull
    @JsonProperty("valeur")
    private int valeur;

}

