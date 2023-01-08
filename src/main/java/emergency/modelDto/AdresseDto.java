package emergency.modelDto;

import emergency.interfacesDefinition.IBaseModelDto;
import emergency.modelDto.GBaseDto;
import emergency.models.Adresse;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

public class AdresseDto extends GBaseDto implements IBaseModelDto {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("rue")
    private String rue;

    @NotNull
    @JsonProperty("ville")
    private String ville;

    @NotNull
    @JsonProperty("etat")
    private String etat;

    @NotNull
    @JsonProperty("codePostal")
    private String codePostal;

    @NotNull
    @JsonProperty("pays")
    private String pays;

    public void setId(Long id) {
        this.id = id;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Long getId() {
        return id;
    }

    public String getRue() {
        return rue;
    }

    public String getVille() {
        return ville;
    }

    public String getEtat() {
        return etat;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public String getPays() {
        return pays;
    }

    public Adresse toModel()
    {
        Adresse model = new Adresse();
        if(this.getId()!=null)
        {
            model.setId(this.getId());
        }
        model.setRue(this.getRue());
        model.setVille(this.getVille());
        model.setEtat(this.getEtat());
        model.setCodePostal(this.getCodePostal());
        model.setPays(this.getPays());
        return model;
    }
}