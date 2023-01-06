package emergency.services.type;

import emergency.models.TypeRessource;
import emergency.models.TypeUrgence;
import emergency.repositories.type.TypeRessourceRepository;
import emergency.repositories.type.TypeUrgenceRepository;
import emergency.services.BaseService;
import org.springframework.stereotype.Service;

@Service
public class TypeUrgenceService extends BaseService {

    private final TypeUrgenceRepository typeUrgenceRepository;

    public TypeUrgenceService(TypeUrgenceRepository typeUrgenceRepository) {
        this.typeUrgenceRepository = typeUrgenceRepository;
        this.baseRepository = typeUrgenceRepository;
    }

    public TypeUrgence getByName(String name)
        {
            try{
                var type =  this.typeUrgenceRepository.findByNom(name);
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

        public TypeUrgence getNonInitie()
        {
            return this.getByName("NON_INITIE");
        }
        public TypeUrgence getDemarre()
        {
            return this.getByName("DEMARRE");
        }
        public TypeUrgence getEnCours()
        {
            return this.getByName("EN_COURS");
        }
        public TypeUrgence getPause()
        {
            return this.getByName("PAUSE");
        }
        public TypeUrgence getArrete()
        {
            return this.getByName("ARRETE");
        }
}