package emergency.services;

import emergency.repositories.PersonneRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonneService extends BaseService {
    private final PersonneRepository personneRepository;

    public PersonneService(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
        this.baseRepository = personneRepository;
    }
}