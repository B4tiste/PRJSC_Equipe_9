package Repositories.Type;

import Models.Incident;
import Models.TypeUrgence;
import Models.Urgence;
import org.springframework.stereotype.Repository;


import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface TypeUrgenceRepository extends PagingAndSortingRepository<TypeUrgence, Long>, CrudRepository<TypeUrgence,Long> {

    List<TypeUrgence> findByNom(@Param("NOM") String nom);

    List<TypeUrgence> findByValeur(@Param("VALEUR") Integer valeur);
}