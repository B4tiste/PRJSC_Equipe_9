package emergency.modelDto;

import emergency.interfacesDefinition.IBaseModelDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class CentreDto implements IBaseModelDto {


    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("nom")
    private String nom;

    @NotNull
    @JsonProperty("isAvailable")
    private Boolean isAvailable;

    @NotNull
    @JsonProperty("latitude")
    private Double latitude;

    @NotNull
    @JsonProperty("longitude")
    private Double longitude;

    @NotNull
    @JsonProperty("ressourceComposanteDtos")
    private List<Long> ressourceComposanteDtos;

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

    public List<Long> getRessourceComposanteDtos() {
        return ressourceComposanteDtos;
    }

}