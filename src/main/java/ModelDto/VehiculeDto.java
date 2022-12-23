package ModelDto;

import InterfacesDefinition.IBaseModelDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.jetbrains.annotations.NotNull;

public class VehiculeDto implements IBaseModelDto {

    @NotNull
    @JsonProperty("capacite")
    private Long capacite;
    @NotNull
    @JsonProperty("latitude")
    private Long latitude;

    @NotNull
    @JsonProperty("longitude")
    private Long longitude;

    public Long getCapacite() {
        return capacite;
    }

    public Long getLatitude() {
        return latitude;
    }

    public Long getLongitude() {
        return longitude;
    }
}