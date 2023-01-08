package emergency.modelDto.sensorRelated;

import emergency.baseReferentiel.ReferentielDefinitions;
import emergency.baseReferentiel.ServiceDefinitions;
import emergency.interfacesDefinition.IBaseModelDto;
import emergency.modelDto.GBaseDto;
import emergency.models.RessourceComposante;
import emergency.models.sensorRelated.Capteur;
import emergency.models.sensorRelated.CapteurDonnees;
import emergency.models.sensorRelated.CapteurType;
import emergency.models.sensorRelated.Microcontroller;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;


public class CapteurDto extends GBaseDto implements IBaseModelDto {

    @NotNull
    @JsonProperty("id")
    private Long id;


    @NotNull
    @JsonProperty("microcontrolleurId")
    private Long microcontrolleurId;

    @NotNull
    @JsonProperty("microcontrolleur")
    private MicrocontrollerDto microcontrolleur;


    @JsonProperty("latitude")
    private Double latitude;


    @JsonProperty("longitude")
    private Double longitude;

    @NotNull
    @JsonProperty("radius")
    private Double radius;


    @NotNull
    @JsonProperty("identifier")
    private String identifier;

    @NotNull
    @JsonProperty("capteurType")
    private CapteurTypeDto capteurType;




    @JsonProperty("capteurTypeId")
    private Long capteurTypeId;


    @JsonProperty("capteurDonnees")
    private List<CapteurDonneesDto> capteurDonnees;



    @JsonProperty("capteurDonneesId")
    private List<Long> capteurDonneesId;

    public void setId(Long id) {
        this.id = id;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public void setCapteurType(CapteurTypeDto capteurType) {
        this.capteurType = capteurType;
    }

    public void setCapteurTypeId(Long capteurTypeId) {
        this.capteurTypeId = capteurTypeId;
    }

    public void setCapteurDonnees(List<CapteurDonneesDto> capteurDonnees) {
        this.capteurDonnees = capteurDonnees;
    }

    public void setCapteurDonneesId(List<Long> capteurDonneesId) {
        this.capteurDonneesId = capteurDonneesId;
    }

    public Long getCapteurTypeId() {
        return capteurTypeId;
    }

    public List<Long> getCapteurDonneesId() {
        return capteurDonneesId;
    }

    public Long getId() {
        return id;
    }

    public Double getRadius() {
        return radius;
    }

    public void setRadius(Double radius) {
        this.radius = radius;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getIdentifier() {
        return identifier;
    }

    public CapteurTypeDto getSensorType() {
        return capteurType;
    }

    public Long getMicrocontrolleurId() {
        return microcontrolleurId;
    }

    public void setMicrocontrolleurId(Long microcontrolleurId) {
        this.microcontrolleurId = microcontrolleurId;
    }

    public MicrocontrollerDto getMicrocontrolleur() {
        return microcontrolleur;
    }

    public void setMicrocontrolleur(MicrocontrollerDto microcontrolleur) {
        this.microcontrolleur = microcontrolleur;
    }


    public List<CapteurDonneesDto> getCapteurDonnees() {
        return capteurDonnees;
    }

    public Capteur toModel()
    {
        Capteur model = new Capteur();
        if(this.getId()!=null)
        {
            model.setId(this.getId());
        }
        List<CapteurDonnees> data = new ArrayList<>();
        Boolean data_present = Boolean.FALSE;
        if(this.getCapteurDonnees()!=null)
        {
            if(this.getCapteurDonnees().size()>0)
            {
                data_present = Boolean.TRUE;
                for(var capteur : this.getCapteurDonnees())
                {
                    try {
                        var dest_capteur = (CapteurDonnees)capteur.toModel();
                        dest_capteur.setCapteur(model);
                        data.add(dest_capteur);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        else if(this.getCapteurDonneesId()!=null)
        {
            if(this.getCapteurDonneesId().size()>0)
            {
                data_present = Boolean.TRUE;

                try {
                    var serv = this.getServices().getCapteurDonneesService();
                    var val = (List<CapteurDonnees>)(List<?>)serv.GetThem(this.getCapteurDonneesId());
                    data = val;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        model.setCapteurDonnees(data);
        if(this.getSensorType()!=null)
        {
            model.setCapteurType((CapteurType) this.getSensorType().toModel());
        }
        else if(this.getCapteurTypeId()!=null)
        {
            model.setCapteurType(ReferentielDefinitions.getCapteurType(this.getCapteurTypeId()));
        }
        if(this.getMicrocontrolleur()!=null)
        {
            model.setMicrocontroller((Microcontroller) this.getMicrocontrolleur().toModel());
        }
        else if(this.getMicrocontrolleurId()!=null)
        {
            model.setMicrocontroller(
                    (Microcontroller) ReferentielDefinitions.serviceDefinitions.getMicrocontrollerService().getById(this.getMicrocontrolleurId())
            );
        }
        if(this.getRadius()!=null)
        {
            model.setRadius(this.getRadius());
        }
        if(this.getIdentifier()!=null)
        {
            model.setIdentifier(this.getIdentifier());
        }

        return model;

    }

}
