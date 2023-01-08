package emergency.repositories;

import emergency.models.Centre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface CentreRepository extends  PagingAndSortingRepository<Centre, Long>, CrudRepository<Centre, Long> {

    List<Centre> findByNom(@Param("NOM") String nom);

    //@Query("SELECT c FROM Centre c ORDER BY SQRT(POW(c.latitude - :latitude, 2) + POW(c.longitude - :longitude, 2)) ASC")
    @Query("SELECT v FROM Vehicule v\n" +
            "WHERE\n" +
            "  (6371 * acos(cos(radians(:latitude)) * cos(radians(v.latitude)) * cos(radians(v.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(v.latitude))))\n" +
            "  < :radius\n" +
            "ORDER BY\n" +
            "  (6371 * acos(cos(radians(:latitude)) * cos(radians(v.latitude)) * cos(radians(v.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(v.latitude)))) ASC")
    List<Centre> findNearestCentres(@Param("latitude") Double latitude, @Param("longitude") Double longitude, Pageable pageable);

}