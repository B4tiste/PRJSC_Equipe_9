package Repositories.Type;

import Models.Incident;
import Models.TypeRessource;
import Models.TypeUrgence;
import Models.Urgence;
import org.springframework.stereotype.Repository;


import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface TypeRessourceRepository extends PagingAndSortingRepository<TypeRessource, Long>, CrudRepository<TypeRessource,Long> {

    List<TypeRessource> findByNom(@Param("NOM") String nom);

    List<TypeRessource> findByValeur(@Param("VALEUR") Integer valeur);
}