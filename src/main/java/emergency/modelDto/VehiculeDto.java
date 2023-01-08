package emergency.modelDto;

import emergency.interfacesDefinition.IBaseModelDto;
import emergency.modelDto.GBaseDto;
import emergency.models.Personne;
import emergency.models.Vehicule;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;


public class VehiculeDto extends RessourceComposanteDto implements IBaseModelDto {

    @NotNull
    @JsonProperty("capacite")
    private Long capacite;

    @NotNull
    @JsonProperty("latitude")
    private Double latitude;

    @NotNull
    @JsonProperty("longitude")
    private Double longitude;

    public void setCapacite(Long capacite) {
        this.capacite = capacite;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }



    public Long getCapacite() {
        return capacite;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Vehicule toModel()
    {
        Vehicule model = new Vehicule();
        if(this.getId()!=null)
        {
            model.setId(this.getId());
        }
        model.setNom(this.getNom());
        model.setAvailable(this.isAvailable());
        model.setCapacite(this.getCapacite());
        model.setLatitude(this.getLatitude());
        model.setLongitude(this.getLongitude());
        List<Personne> data = new ArrayList<>();
        Boolean data_present = Boolean.FALSE;
        if(this.getPersonnes()!=null)
        {
            if(this.getPersonnes().size()>0)
            {
                data_present = Boolean.TRUE;
                for(var personne : this.getPersonnes())
                {
                    try {
                        var dest_personne = (Personne)personne.toModel();
                        dest_personne.setRessourceComposante(model);
                        data.add(dest_personne);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else if(this.getPersonnesId()!=null)
        {
            if(this.getPersonnesId().size()>0)
            {
                data_present = Boolean.TRUE;

                try {
                    var serv = this.getServices().getPersonneService();
                    var val = (List<Personne>)(List<?>)serv.GetThem(this.getPersonnesId());
                    data = val;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        model.setPersonnes(data);
        return model;
    }
}