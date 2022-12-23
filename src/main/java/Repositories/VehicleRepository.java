package Repositories;


import Models.Urgence;
import Models.Vehicule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends PagingAndSortingRepository<Vehicule, Long>, CrudRepository<Vehicule,Long> {

    List<Vehicule> findByNom(@Param("NOM") String nom);
}