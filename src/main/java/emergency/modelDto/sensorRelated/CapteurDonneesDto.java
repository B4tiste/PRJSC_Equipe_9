package emergency.modelDto.sensorRelated;

import emergency.interfacesDefinition.IBaseModelDto;
import emergency.modelDto.GBaseDto;
import emergency.models.sensorRelated.CapteurDonnees;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

public class CapteurDonneesDto extends GBaseDto implements IBaseModelDto {
    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("identifier")
    private String identifier;

    @NotNull
    @JsonProperty("valeur")
    private String valeur;

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }
/*@NotNull
    @JsonProperty("capteur")
    private Long capteur;*/

    public Long getId() {
        return id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public String getValeur() {
        return valeur;
    }

   /* public Long getCapteur() {
        return capteur;
    }*/

    public CapteurDonnees toModel()
    {
        CapteurDonnees model = new CapteurDonnees();
        if(this.getId()!=null)
        {
            model.setId(this.getId());
        }
        model.setIdentifier(this.getIdentifier());
        model.setValeur(this.getValeur());
        return model;
    }
}