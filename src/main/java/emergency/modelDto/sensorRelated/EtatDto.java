package emergency.modelDto.sensorRelated;

import emergency.interfacesDefinition.IBaseModelDto;
import emergency.modelDto.GBaseDto;
import emergency.models.sensorRelated.Etat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

public class EtatDto extends GBaseDto implements IBaseModelDto {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("value")
    private int value;

    @NotNull
    @JsonProperty("nom")
    private String nom;

    public void setId(Long id) {
        this.id = id;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public String getNom() {
        return nom;
    }

    public Etat toModel()
    {
        Etat model = new Etat();
        if(this.getId()!=null)
        {
            model.setId(this.getId());
        }
        model.setValeur(this.getValue());
        model.setNom(this.getNom());
        return model;
    }
}
