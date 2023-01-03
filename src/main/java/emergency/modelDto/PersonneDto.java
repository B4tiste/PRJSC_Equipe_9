package emergency.modelDto;

import emergency.interfacesDefinition.IBaseModelDto;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Long roleDto;

    @NotNull
    @JsonProperty("ressourceComposanteDto")
    private Long ressourceComposanteDto;

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public Long getRoleDto() {
        return roleDto;
    }

    public Long getRessourceComposanteDto() {
        return ressourceComposanteDto;
    }
}