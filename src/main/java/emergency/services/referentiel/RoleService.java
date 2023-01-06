package emergency.services.referentiel;

import emergency.repositories.referentiel.RoleRepository;
import emergency.services.BaseService;
import org.springframework.stereotype.Service;

@Service
public class RoleService extends BaseService  {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        this.baseRepository = roleRepository;
    }
}