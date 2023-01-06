package emergency.services.referentiel;

import emergency.models.Statut;
import emergency.repositories.referentiel.StatutRepository;
import emergency.services.BaseService;
import org.springframework.stereotype.Service;

@Service
public class StatutService extends BaseService {

    private final StatutRepository statutRepository;

    public StatutService(StatutRepository statutRepository) {
        this.statutRepository = statutRepository;
        this.baseRepository = statutRepository;
    }

    public Statut getByName(String name)
    {
        try{
            var statuts =  this.statutRepository.findByNom(name);
            if(statuts.isEmpty())
            {
                return null;
            }
            else
            {
                return statuts.get(0);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }


}