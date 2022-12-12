package ModelDto;

import InterfacesDefinition.IBaseModel;
import InterfacesDefinition.IBaseModelDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;


public class PersonneDto  implements IBaseModelDto {
    @NotNull
    @JsonProperty("id")
    private Long id;
    @NotNull
    @JsonProperty("nom")
    private String nom;
    @NotNull
    @JsonProperty("prenom")
    private String prenom;
    @NotNull
    @JsonProperty("roleDto")
    private RoleDto roleDto;
}