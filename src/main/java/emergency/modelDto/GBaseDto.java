package emergency.modelDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import emergency.baseReferentiel.ReferentielDefinitions;
import emergency.baseReferentiel.ServiceDefinitions;
import org.springframework.beans.factory.annotation.Autowired;

public class GBaseDto {

    @JsonIgnore
    @Autowired
    private ServiceDefinitions services;

    public ServiceDefinitions getServices() {
        if(services == null)
        {
            return ReferentielDefinitions.serviceDefinitions;
        }
        return services;
    }

    public void setServices(ServiceDefinitions services) {
        this.services = services;
    }
}