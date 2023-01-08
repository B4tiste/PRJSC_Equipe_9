package emergency.modelDto;

import emergency.interfacesDefinition.IBaseModelDto;
import emergency.modelDto.GBaseDto;
import emergency.models.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;


public class RoleDto extends GBaseDto implements IBaseModelDto {
    @NotNull
    @JsonProperty("id")
    private Long id;
    @NotNull
    @JsonProperty("nom")
    private String nom;
    @NotNull
    @JsonProperty("valeur")
    private Long valeur;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setValeur(Long valeur) {
        this.valeur = valeur;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Long getValeur() {
        return valeur;
    }

    public Role toModel()
    {
        Role model = new Role();
        if(this.getId()!=null)
        {
            model.setId(this.getId());
        }
        model.setNom(this.getNom());
        model.setValeur(this.getValeur());
        return model;
    }
}