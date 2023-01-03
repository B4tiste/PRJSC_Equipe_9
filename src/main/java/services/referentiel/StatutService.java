package services.referentiel;

import models.Statut;
import repositories.referentiel.StatutRepository;
import services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatutService extends BaseService {

    @Autowired
    private StatutRepository statutRepository;

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