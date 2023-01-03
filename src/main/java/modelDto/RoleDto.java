package modelDto;

import interfacesDefinition.IBaseModelDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;


public class RoleDto implements IBaseModelDto {
    @NotNull
    @JsonProperty("id")
    private Long id;
    @NotNull
    @JsonProperty("nom")
    private String nom;
    @NotNull
    @JsonProperty("valeur")
    private Long valeur;

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Long getValeur() {
        return valeur;
    }
}