package ModelDto.SensorRelated;

import InterfacesDefinition.IBaseModelDto;
import Models.SensorRelated.Capteur;
import Models.SensorRelated.Etat;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MicrocontrollerDto implements IBaseModelDto {

    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("latitude")
    private Float latitude;

    @NotNull
    @JsonProperty("longitude")
    private Float longitude;

    @NotNull
    @JsonProperty("nom")
    private String nom;

    @NotNull
    @JsonProperty("etat")
    private Long etat;

    @NotNull
    @JsonProperty("capteurs")
    private List<Long> capteurs;

    public Long getId() {
        return id;
    }

    public Float getLatitude() {
        return latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public String getNom() {
        return nom;
    }

    public Long getEtat() {
        return etat;
    }

    public List<Long> getCapteurs() {
        return capteurs;
    }
}