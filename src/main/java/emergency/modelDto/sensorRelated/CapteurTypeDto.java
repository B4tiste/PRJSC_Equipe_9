package emergency.modelDto.sensorRelated;

import emergency.interfacesDefinition.IBaseModelDto;
import emergency.modelDto.GBaseDto;
import emergency.models.sensorRelated.CapteurType;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.jetbrains.annotations.NotNull;

public class CapteurTypeDto extends GBaseDto implements IBaseModelDto {
    @NotNull
    @JsonProperty("id")
    private Long id;


    @NotNull
    @JsonProperty("value")
    private Integer value;

    @NotNull
    @JsonProperty("type")
    private String type;

    public void setId(Long id) {
        this.id = id;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public Integer getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public CapteurType toModel()
    {
        CapteurType model = new CapteurType();
        if(this.getId()!=null)
        {
            model.setId(this.getId());
        }
        model.setValeur(this.getValue());
        model.setType(this.getType());
        return model;
    }
}