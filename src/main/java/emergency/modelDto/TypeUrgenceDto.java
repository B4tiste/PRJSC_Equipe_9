package emergency.modelDto;

import emergency.interfacesDefinition.IBaseModelDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

public class TypeUrgenceDto implements IBaseModelDto {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("nom")
    private String nom;

    @NotNull
    @JsonProperty("valeur")
    private int valeur;

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getValeur() {
        return valeur;
    }
}