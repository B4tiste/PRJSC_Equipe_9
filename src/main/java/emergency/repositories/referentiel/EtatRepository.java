package emergency.repositories.referentiel;

import emergency.models.Priorite;
import emergency.models.sensorRelated.Etat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface EtatRepository extends PagingAndSortingRepository<Etat, Long>, CrudRepository<Etat, Long> {

    List<Etat> findByNom(@Param("NOM") String name);

}
