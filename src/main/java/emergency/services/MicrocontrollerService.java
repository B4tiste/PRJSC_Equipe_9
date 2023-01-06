package emergency.services;

import emergency.repositories.MicrocontrollerRepository;
import org.springframework.stereotype.Service;

@Service
public class MicrocontrollerService extends BaseService {
    private final MicrocontrollerRepository microcontrollerRepository;

    public MicrocontrollerService(MicrocontrollerRepository microcontrollerRepository) {
        this.microcontrollerRepository = microcontrollerRepository;
        this.baseRepository = microcontrollerRepository;
    }
}