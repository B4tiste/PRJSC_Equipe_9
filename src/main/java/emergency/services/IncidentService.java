package emergency.services;

import emergency.models.Centre;
import emergency.models.Incident;
import emergency.repositories.IncidentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidentService extends BaseService {

    private final IncidentRepository incidentRepository;

    public IncidentService(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
        this.baseRepository = incidentRepository;
    }

    public List<Incident> FindIncidentInRadius(
            Double lng, Double lat,
            Double radius
    )
    {
        try{
            return this.incidentRepository.findByLatitudeAndLongitudeWithinRadius(lng, lat, radius);
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
