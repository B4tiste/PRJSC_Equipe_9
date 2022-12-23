package Repositories;

import Models.Incident;
import org.springframework.stereotype.Repository;


import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface IncidentRepository extends PagingAndSortingRepository<Incident, Long>, CrudRepository<Incident,Long> {

    List<Incident> findByNom(@Param("NOM") String nom);

}


