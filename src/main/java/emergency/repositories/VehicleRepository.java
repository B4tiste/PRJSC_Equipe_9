package emergency.repositories;


import emergency.models.Centre;
import emergency.models.Urgence;
import emergency.models.Vehicule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends PagingAndSortingRepository<Vehicule, Long>, CrudRepository<Vehicule, Long> {

    List<Vehicule> findByNom(@Param("NOM") String nom);

    //@Query("SELECT c FROM Vehicule c ORDER BY SQRT((c.latitude - :latitude)*(c.latitude - :latitude) + (c.longitude - :longitude, 2)*(c.longitude - :longitude, 2)) ASC")
    @Query("SELECT v FROM Vehicule v\n" +
            "WHERE\n" +
            "  (6371 * acos(cos(radians(:latitude)) * cos(radians(v.latitude)) * cos(radians(v.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(v.latitude))))\n" +
            "  < :radius\n" +
            "ORDER BY\n" +
            "  (6371 * acos(cos(radians(:latitude)) * cos(radians(v.latitude)) * cos(radians(v.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(v.latitude)))) ASC")
    List<Vehicule> findNearestVehicules(@Param("latitude") double latitude, @Param("longitude") double longitude,  Pageable pageable);
    //CAST(:latitude AS NUMERIC)

    @Query("SELECT v FROM Vehicule v WHERE v.latitude BETWEEN :latitude - :radius AND :latitude + :radius AND v.longitude BETWEEN :longitude - :radius AND :longitude + :radius ORDER BY (v.latitude - :latitude) * (v.latitude - :latitude) + (v.longitude - :longitude) * (v.longitude - :longitude)")
    Page<Vehicule> findNearestUsingRadius(@Param("latitude") Double latitude, @Param("longitude") Double longitude, @Param("radius") Double radius, Pageable pageable);

    @Query("SELECT v FROM Vehicule v ORDER BY (v.latitude - :latitude) * (v.latitude - :latitude) + (v.longitude - :longitude) * (v.longitude - :longitude)")
    Page<Vehicule> findNearest(@Param("latitude") Double latitude, @Param("longitude") Double longitude, Pageable pageable);
}