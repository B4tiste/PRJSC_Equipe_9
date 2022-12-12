package ModelDto.SensorRelated;

import InterfacesDefinition.IBaseModel;
import InterfacesDefinition.IBaseModelDto;
import Models.SensorRelated.CapteurDonnees;
import Models.SensorRelated.CapteurType;
import Models.SensorRelated.Microcontroller;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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
    private CapteurTypeDto sensorType;

    @NotNull
    @JsonProperty("capteurDonnees")
    private List<CapteurDonnees> capteurDonnees;
}
