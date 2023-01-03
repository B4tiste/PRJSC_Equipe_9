package emergency.modelDto;

import emergency.interfacesDefinition.IBaseModelDto;
import com.fasterxml.jackson.annotation.JsonProperty;
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