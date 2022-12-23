package Services.Referentiel;

import Models.Priorite;
import Models.Statut;
import Models.TypeRessource;
import Repositories.Referentiel.PrioriteRepository;
import Repositories.Referentiel.StatutRepository;
import Repositories.Type.TypeRessourceRepository;
import Services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import InterfacesDefinition.IBaseService;
import org.springframework.stereotype.Service;

@Service
public class StatutService extends BaseService {

    @Autowired
    private StatutRepository statutRepository;

    public Statut getByName(String name)
    {
        try{
            var statuts =  this.statutRepository.findByName(name);
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