package Repositories;

import Models.Incident;
import Models.Ressource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RessourceRepository extends PagingAndSortingRepository<Ressource, Long>, CrudRepository<Ressource,Long> {

    List<Ressource> findByNom(@Param("NOM") String nom);

}