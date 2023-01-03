package repositories.type;

import models.TypeUrgence;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository
@Component
public interface TypeUrgenceRepository extends PagingAndSortingRepository<TypeUrgence, Long>, CrudRepository<TypeUrgence,Long> {

    List<TypeUrgence> findByNom(@Param("NOM") String nom);

    List<TypeUrgence> findByValeur(@Param("VALEUR") Integer valeur);
}