package Repositories;

import Models.Incident;
import Models.Urgence;
import org.springframework.stereotype.Repository;


import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface UrgenceRepository extends PagingAndSortingRepository<Urgence, Long>, CrudRepository<Urgence,Long> {

    List<Urgence> findByNom(@Param("NOM") String nom);
}


