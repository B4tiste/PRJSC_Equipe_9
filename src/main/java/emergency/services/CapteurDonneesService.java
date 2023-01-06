package emergency.services;

import emergency.repositories.CapteurDonneesRepository;
import emergency.repositories.CapteurRepository;
import org.springframework.stereotype.Service;

@Service
public class CapteurDonneesService extends BaseService {

    private final CapteurDonneesRepository capteurDonneesRepository;

    public CapteurDonneesService(CapteurDonneesRepository capteurDonneesRepository) {
        this.capteurDonneesRepository = capteurDonneesRepository;
        this.baseRepository = capteurDonneesRepository;
    }
}