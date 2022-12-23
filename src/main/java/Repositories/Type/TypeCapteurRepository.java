package Repositories.Type;

import Models.SensorRelated.CapteurType;
import Models.TypeRessource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeCapteurRepository extends PagingAndSortingRepository<CapteurType, Long>, CrudRepository<CapteurType,Long> {

    List<CapteurType> findByNom(@Param("NOM") String nom);

    List<CapteurType> findByValeur(@Param("VALEUR") Integer valeur);
}