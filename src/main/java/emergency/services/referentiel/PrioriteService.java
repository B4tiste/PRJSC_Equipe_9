package emergency.services.referentiel;

import emergency.models.Priorite;
import emergency.repositories.referentiel.PrioriteRepository;
import emergency.services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrioriteService extends BaseService {

    @Autowired
    private PrioriteRepository prioriteRepository;

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