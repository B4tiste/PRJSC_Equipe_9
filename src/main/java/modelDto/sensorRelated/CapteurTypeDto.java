package modelDto.sensorRelated;

import interfacesDefinition.IBaseModelDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

public class CapteurTypeDto implements IBaseModelDto {
    @NotNull
    @JsonProperty("id")
    private Long id;


    @NotNull
    @JsonProperty("value")
    private String value;

    public Long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }
}