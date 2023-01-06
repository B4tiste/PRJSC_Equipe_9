package emergency.services;

import emergency.baseReferentiel.ServiceDefinitions;
import emergency.models.Centre;
import emergency.repositories.CentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CentreService extends BaseService {

    @Autowired
    private final CentreRepository centreRepository;

    public CentreService(CentreRepository centreRepository) {
        this.centreRepository = centreRepository;
        this.baseRepository = centreRepository;
    }
}