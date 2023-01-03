package modelDto.sensorRelated;

import interfacesDefinition.IBaseModelDto;
import models.sensorRelated.Microcontroller;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class CapteurDto implements IBaseModelDto {

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
    @JsonProperty("microcontroller")
    private Microcontroller microcontroller;

    @NotNull
    @JsonProperty("identifier")
    private String identifier;

    @NotNull
    @JsonProperty("sensorType")
    private Long sensorType;

    public Long getId() {
        return id;
    }

    public Float getLatitude() {
        return latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public Microcontroller getMicrocontroller() {
        return microcontroller;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Long getSensorType() {
        return sensorType;
    }

    public List<Long> getCapteurDonnees() {
        return capteurDonnees;
    }

    @NotNull
    @JsonProperty("capteurDonnees")
    private List<Long> capteurDonnees;
}
