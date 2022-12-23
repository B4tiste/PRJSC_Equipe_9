package Services.Referentiel;

import Models.Priorite;
import Models.TypeRessource;
import Repositories.Referentiel.PrioriteRepository;
import Repositories.Type.TypeRessourceRepository;
import Services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import InterfacesDefinition.IBaseService;
import org.springframework.stereotype.Service;

@Service
public class PrioriteService extends BaseService {

    @Autowired
    private PrioriteRepository prioriteRepository;

    public Priorite getByName(String name)
    {
        try{
            var priorites =  this.prioriteRepository.findByName(name);
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