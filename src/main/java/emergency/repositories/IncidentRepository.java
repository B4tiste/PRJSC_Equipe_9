package emergency.repositories;

import emergency.models.Incident;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository
@Component
public interface IncidentRepository extends  PagingAndSortingRepository<Incident, Long>, CrudRepository<Incident, Long>  {

    List<Incident> findByNom(@Param("NOM") String nom);

    @Query("SELECT i FROM Incident i WHERE (6371 * acos(cos(radians(:latitude)) * cos(radians(i.latitude)) * cos(radians(i.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(i.latitude)))) < :radius")
    List<Incident> findByLatitudeAndLongitudeWithinRadius(@Param("latitude") Double latitude, @Param("longitude") Double longitude, @Param("radius") Double radius);

}


