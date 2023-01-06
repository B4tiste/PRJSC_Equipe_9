package emergency.services.type;

import emergency.models.TypeRessource;
import emergency.repositories.type.TypeRessourceRepository;
import emergency.services.BaseService;
import org.springframework.stereotype.Service;

@Service
public class TypeRessourceService extends BaseService {

        private final TypeRessourceRepository typeRessourceRepository;

        public TypeRessourceService(TypeRessourceRepository typeRessourceRepository) {
                this.typeRessourceRepository = typeRessourceRepository;
                this.baseRepository = typeRessourceRepository;
        }

        public TypeRessource getByType(String type_d)
        {
                try{
                        var type =  this.typeRessourceRepository.findByType(type_d);
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