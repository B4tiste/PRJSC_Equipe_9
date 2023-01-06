package emergency.repositories;

import emergency.models.sensorRelated.Capteur;
import emergency.models.sensorRelated.CapteurDonnees;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Component
public interface CapteurDonneesRepository extends PagingAndSortingRepository<CapteurDonnees, Long>, CrudRepository<CapteurDonnees, Long> {

    List<CapteurDonnees> findByIdentifier(@Param("IDENTIFIER") String identifier);

}