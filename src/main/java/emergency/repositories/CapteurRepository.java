package emergency.repositories;

import emergency.models.Centre;
import emergency.models.sensorRelated.Capteur;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface CapteurRepository extends PagingAndSortingRepository<Capteur, Long>, CrudRepository<Capteur, Long> {

    List<Capteur> findByIdentifier(@Param("IDENTIFIER") String identifier);

}