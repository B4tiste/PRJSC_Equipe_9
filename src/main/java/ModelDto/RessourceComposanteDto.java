package ModelDto;

import InterfacesDefinition.IBaseModel;
import InterfacesDefinition.IBaseModelDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public boolean isAvailable() {
        return isAvailable;
    }
}