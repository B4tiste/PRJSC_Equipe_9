package Repositories;

import Models.Centre;
import Models.Incident;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CentreRepository extends PagingAndSortingRepository<Centre, Long>, CrudRepository<Centre,Long> {

    List<Centre> findByNom(@Param("NOM") String nom);

}