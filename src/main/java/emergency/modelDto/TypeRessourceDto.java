package emergency.modelDto;

import emergency.interfacesDefinition.IBaseModelDto;
import emergency.modelDto.GBaseDto;
import emergency.models.TypeRessource;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;


public class TypeRessourceDto extends GBaseDto implements IBaseModelDto {

    @NotNull
    @JsonProperty("id")
    private Long id;
    @NotNull
    @JsonProperty("nom")
    private String nom;
    @NotNull
    @JsonProperty("valeur")
    private int valeur;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setValeur(int valeur) {
        this.valeur = valeur;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getValeur() {
        return valeur;
    }

    public TypeRessource toModel()
    {
        TypeRessource model = new TypeRessource();
        if(this.getId()!=null)
        {
            model.setId(this.getId());
        }
        model.setType(this.getNom());
        model.setValeur(this.getValeur());
        return model;
    }
}