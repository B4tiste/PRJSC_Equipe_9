package emergency.services;

import emergency.repositories.IncidentRepository;
import org.springframework.stereotype.Service;

@Service
public class IncidentService extends BaseService {

    private final IncidentRepository incidentRepository;

    public IncidentService(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
        this.baseRepository = incidentRepository;
    }
}
