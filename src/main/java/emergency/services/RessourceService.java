package emergency.services;

import emergency.repositories.RessourceRepository;
import org.springframework.stereotype.Service;

@Service
public class RessourceService extends BaseService {
    private final RessourceRepository ressourceRepository;

    public RessourceService(RessourceRepository ressourceRepository) {
        this.ressourceRepository = ressourceRepository;
        this.baseRepository = ressourceRepository;
    }
}