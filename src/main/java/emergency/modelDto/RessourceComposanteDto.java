package emergency.modelDto;

import emergency.interfacesDefinition.IBaseModelDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;


public class RessourceComposanteDto  implements IBaseModelDto {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("nom")
    private String nom;

    @NotNull
    @JsonProperty("isAvailable")
    private boolean isAvailable;

    @NotNull
    @JsonProperty("ressource")
    private Long ressource;

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public Long getRessource() {
        return ressource;
    }

}