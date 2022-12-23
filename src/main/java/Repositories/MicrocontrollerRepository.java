package Repositories;

import Models.Incident;
import Models.SensorRelated.Microcontroller;
import org.springframework.stereotype.Repository;


import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface MicrocontrollerRepository extends PagingAndSortingRepository<Microcontroller, Long>, CrudRepository<Microcontroller,Long> {

    List<Microcontroller> findByNom(@Param("NOM") String nom);

}