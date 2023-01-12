package emergency.services;

import emergency.models.Centre;
import emergency.repositories.CapteurRepository;
import emergency.repositories.IncidentRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CapteurService extends BaseService {

    private final CapteurRepository capteurRepository;

    public CapteurService(CapteurRepository capteurRepository) {
        this.capteurRepository = capteurRepository;
        this.baseRepository = capteurRepository;
    }



}