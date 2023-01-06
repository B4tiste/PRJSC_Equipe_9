package emergency.services;

import emergency.repositories.VehicleRepository;
import org.springframework.stereotype.Service;

@Service
public class VehicleService extends BaseService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;

    }
}