package emergency.services.referentiel;

import emergency.models.sensorRelated.Etat;
import emergency.repositories.referentiel.EtatRepository;
import emergency.services.BaseService;
import org.springframework.stereotype.Service;

@Service
public class EtatService extends BaseService {

    private final EtatRepository etatRepository;

    public EtatService(EtatRepository etatRepository) {
        this.etatRepository = etatRepository;
        this.baseRepository = etatRepository;
    }

    public Etat getByName(String name)
    {
        try{
            var etats =  this.etatRepository.findByNom(name);
            if(etats.isEmpty())
            {
                return null;
            }
            else
            {
                return etats.get(0);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }


}