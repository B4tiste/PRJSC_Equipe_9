package ModelDto;

import InterfacesDefinition.IBaseModel;
import InterfacesDefinition.IBaseModelDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
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
    private List<RessourceComposanteDto> ressourceComposanteDtos;

}