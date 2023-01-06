package emergency.services;

import emergency.repositories.RessourceComposanteRepository;
import org.springframework.stereotype.Service;

@Service
public class RessourceComposanteService extends BaseService {
    private final RessourceComposanteRepository ressourceComposanteRepository;

    public RessourceComposanteService(RessourceComposanteRepository ressourceComposanteRepository) {
        this.ressourceComposanteRepository = ressourceComposanteRepository;
        this.baseRepository = ressourceComposanteRepository;
    }
}