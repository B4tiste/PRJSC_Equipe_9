package emergency.modelDto;

import emergency.interfacesDefinition.IBaseModelDto;
import emergency.modelDto.GBaseDto;
import emergency.models.Adresse;
import emergency.models.Centre;
import com.fasterxml.jackson.annotation.JsonProperty;
import emergency.models.Ressource;
import emergency.models.RessourceComposante;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class CentreDto extends GBaseDto implements IBaseModelDto {


    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("nom")
    private String nom;



    @JsonProperty("adresse")
    private AdresseDto adresse;

    @JsonProperty("adresseId")
    private Long adresseId;



    @NotNull
    @JsonProperty("isAvailable")
    private Boolean isAvailable;


    @JsonProperty("latitude")
    private Double latitude;


    @JsonProperty("longitude")
    private Double longitude;


    @JsonProperty("ressource")
    private List<RessourceDto> ressource;

    @JsonProperty("ressourceId")
    private List<Long> ressourceId;

    public void setId(Long id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(AdresseDto adresse) {
        this.adresse = adresse;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setRessource(List<RessourceDto> ressource) {
        this.ressource = ressource;
    }

    public void setRessourceId(List<Long> ressourceId) {
        this.ressourceId = ressourceId;
    }

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public List<RessourceDto> getRessource() {
        return ressource;
    }

    public List<Long> getRessourceId() {
        return ressourceId;
    }

    public AdresseDto getAdresse() {
        return adresse;
    }

    public Long getAdresseId() {
        return adresseId;
    }

    public void setAdresseId(Long adresseId) {
        this.adresseId = adresseId;
    }

    public Centre toModel()
    {
        Centre model = new Centre();
        if(this.getId()!=null)
        {
            model.setId(this.getId());
        }
        model.setNom(this.getNom());
        if(this.getAdresse()!=null)
        {
            try {
                model.setAdresse((Adresse) this.getAdresse().toModel());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else if(this.getAdresseId()!=null)
        {
            try {
                model.setAdresse((Adresse) this.getServices().getAdresseService().getById(this.getAdresseId()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        model.setAvailable(this.getAvailable());
        model.setLatitude(this.getLatitude());
        model.setLongitude(this.getLongitude());
        List<Ressource> data = new ArrayList<>();
        Boolean data_present = Boolean.FALSE;
        if(this.getRessource()!=null)
        {
            if(this.getRessource().size()>0)
            {
                data_present = Boolean.TRUE;
                for(var ressource : this.getRessource())
                {
                    try {
                        var dest_ressource = (Ressource)ressource.toModel();
                        dest_ressource.setCentre(model);
                        data.add(dest_ressource);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else if(this.getRessourceId()!=null)
        {
            if(this.getRessourceId().size()>0)
            {
                data_present = Boolean.TRUE;

                try {
                    var serv = this.getServices().getRessourceService();
                    var val = (List<Ressource>)(List<?>)serv.GetThem(this.getRessourceId());
                    data = val;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        model.setRessource(data);
        return model;
    }
}