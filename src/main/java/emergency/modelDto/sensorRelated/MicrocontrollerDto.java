package emergency.modelDto.sensorRelated;

import emergency.baseReferentiel.ReferentielDefinitions;
import emergency.interfacesDefinition.IBaseModelDto;
import emergency.modelDto.GBaseDto;
import emergency.models.RessourceComposante;
import emergency.models.sensorRelated.Capteur;
import emergency.models.sensorRelated.Etat;
import emergency.models.sensorRelated.Microcontroller;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MicrocontrollerDto extends GBaseDto implements IBaseModelDto {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("latitude")
    private Double latitude;

    @NotNull
    @JsonProperty("longitude")
    private Double longitude;

    @NotNull
    @JsonProperty("nom")
    private String nom;

    @NotNull
    @JsonProperty("etat")
    private EtatDto etat;

    @JsonProperty("etatId")
    private Long etatId;


    @JsonProperty("capteurs")
    private List<CapteurDto> capteurs;

    @JsonProperty("capteursId")
    private List<Long> capteursId;

    public void setId(Long id) {
        this.id = id;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setEtat(EtatDto etat) {
        this.etat = etat;
    }

    public void setEtatId(Long etatId) {
        this.etatId = etatId;
    }

    public void setCapteurs(List<CapteurDto> capteurs) {
        this.capteurs = capteurs;
    }

    public void setCapteursId(List<Long> capteursId) {
        this.capteursId = capteursId;
    }

    public Long getId() {
        return id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getNom() {
        return nom;
    }

    public EtatDto getEtat() {
        return etat;
    }

    public List<CapteurDto> getCapteurs() {
        return capteurs;
    }

    public Long getEtatId() {
        return etatId;
    }

    public List<Long> getCapteursId() {
        return capteursId;
    }

    public Microcontroller toModel()
    {
        Microcontroller model = new Microcontroller();
        if(this.getId()!=null)
        {
            model.setId(this.getId());
        }
        model.setLatitude(this.getLatitude());
        model.setLongitude(this.getLongitude());
        model.setNom(this.getNom());
        if(this.getEtat()!=null)
        {
            model.setEtat((Etat) this.getEtat().toModel());
        }
        else if(this.getEtatId()!=null)
        {
            model.setEtat(ReferentielDefinitions.getEtat(this.getEtatId()));
        }
        List<Capteur> data = new ArrayList<>();
        Boolean data_present = Boolean.FALSE;
        if(this.getCapteurs()!=null)
        {
            if(this.getCapteurs().size()>0)
            {
                data_present = Boolean.TRUE;
                for(var capteur : this.getCapteurs())
                {
                    var dest_capteur = (Capteur)capteur.toModel();
                    dest_capteur.setMicrocontroller(model);
                    data.add(dest_capteur);
                }
            }
        }
        else if(this.getCapteursId()!=null)
        {
            if(this.getCapteursId().size()>0)
            {
                data_present = Boolean.TRUE;

                try {
                    var serv = this.getServices().getCapteurService();
                    var val = (List<Capteur>)(List<?>)serv.GetThem(this.getCapteursId());
                    data = val;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        model.setCapteurs(data);
        return model;
    }
}