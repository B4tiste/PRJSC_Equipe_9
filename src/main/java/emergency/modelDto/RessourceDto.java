package emergency.modelDto;

import emergency.interfacesDefinition.IBaseModelDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

import java.util.List;

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


}
