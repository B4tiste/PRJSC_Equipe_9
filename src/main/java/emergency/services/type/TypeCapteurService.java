package emergency.services.type;

import emergency.repositories.type.TypeCapteurRepository;
import emergency.services.BaseService;
import org.springframework.stereotype.Service;

@Service
public class TypeCapteurService extends BaseService {

    private final TypeCapteurRepository typeCapteurRepository;

    public TypeCapteurService(TypeCapteurRepository typeCapteurRepository) {
        this.typeCapteurRepository = typeCapteurRepository;
        this.baseRepository = typeCapteurRepository;
    }
}