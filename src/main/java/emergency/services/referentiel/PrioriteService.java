package emergency.services.referentiel;

import emergency.models.Priorite;
import emergency.repositories.referentiel.PrioriteRepository;
import emergency.services.BaseService;
import org.springframework.stereotype.Service;

@Service
public class PrioriteService extends BaseService {

    private final PrioriteRepository prioriteRepository;

    public PrioriteService(PrioriteRepository prioriteRepository) {
        this.prioriteRepository = prioriteRepository;
        this.baseRepository = prioriteRepository;
    }

    public Priorite getByName(String name)
    {
        try{
            var priorites =  this.prioriteRepository.findByNom(name);
            if(priorites.isEmpty())
            {
                return null;
            }
            else
            {
                return priorites.get(0);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }


}