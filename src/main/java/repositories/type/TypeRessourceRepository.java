package repositories.type;

import models.TypeRessource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository
@Component
public interface TypeRessourceRepository extends PagingAndSortingRepository<TypeRessource, Long>, CrudRepository<TypeRessource,Long> {

    List<TypeRessource> findByNom(@Param("NOM") String nom);

    List<TypeRessource> findByValeur(@Param("VALEUR") Integer valeur);
}