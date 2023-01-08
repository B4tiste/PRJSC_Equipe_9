package emergency.services;

import emergency.baseReferentiel.ServiceDefinitions;
import emergency.models.Adresse;
import emergency.models.Centre;
import emergency.models.Incident;
import emergency.models.Urgence;
import emergency.repositories.CentreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class CentreService extends BaseService {

    @Autowired
    private final CentreRepository centreRepository;

    public CentreService(CentreRepository centreRepository) {
        this.centreRepository = centreRepository;
        this.baseRepository = centreRepository;
    }
    public List<Centre> FindCenters(
            Double lng, Double lat,
            org.springframework.data.domain.Pageable pageable
    )
    {
        try{
            return this.centreRepository.findNearestCentres(lng, lat, pageable);
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}