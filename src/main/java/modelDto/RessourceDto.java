package modelDto;

import interfacesDefinition.IBaseModelDto;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Long typeRessourceDto;

    @NotNull
    @JsonProperty("statutDto")
    private Long statutDto;

    @NotNull
    @JsonProperty("ressourceComposanteDto")
    private Long ressourceComposanteDto;



    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Long getTypeRessourceDto() {
        return typeRessourceDto;
    }

    public Long getStatutDto() {
        return statutDto;
    }

    public Long getRessourceComposanteDto() {
        return ressourceComposanteDto;
    }
}
