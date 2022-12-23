package Repositories;

import Models.Personne;
import Models.Ressource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonneRepository extends PagingAndSortingRepository<Personne, Long>, CrudRepository<Personne,Long> {

    List<Personne> findByNom(@Param("NOM") String nom);

}