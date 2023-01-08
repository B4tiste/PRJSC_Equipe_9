package emergency.services;

import emergency.models.Centre;
import emergency.models.Vehicule;
import emergency.repositories.VehicleRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService extends BaseService {
    private final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;

    }

    public List<Vehicule> FindVehicules(
            Double lng, Double lat,
            Pageable pageable
    )
    {
        try{
            return this.vehicleRepository.findNearestVehicules(lng, lat, pageable);
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}