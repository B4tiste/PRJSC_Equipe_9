package services.type;

import models.TypeRessource;
import repositories.type.TypeRessourceRepository;
import services.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeRessourceService extends BaseService {

        @Autowired
        private TypeRessourceRepository typeRessourceRepository;

        public TypeRessource getByName(String name)
        {
                try{
                        var type =  this.typeRessourceRepository.findByNom(name);
                        if(type.isEmpty())
                        {
                                return null;
                        }
                        else
                        {
                                return type.get(0);
                        }
                }
                catch (Exception e)
                {
                        e.printStackTrace();
                        return null;
                }

        }


}