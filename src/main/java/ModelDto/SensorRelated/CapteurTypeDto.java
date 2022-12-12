package ModelDto.SensorRelated;

import InterfacesDefinition.IBaseModel;
import InterfacesDefinition.IBaseModelDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

public class CapteurTypeDto implements IBaseModelDto {
    @NotNull
    @JsonProperty("id")
    private Long id;

    @NotNull
    @JsonProperty("value")
    private String value;
}