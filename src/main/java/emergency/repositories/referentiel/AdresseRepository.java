package emergency.repositories.referentiel;

import emergency.models.*;
import org.springframework.stereotype.Repository;


import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface AdresseRepository extends PagingAndSortingRepository<Adresse, Long>, CrudRepository<Adresse,Long> {

    List<Adresse> findByRue(@Param("RUE") String rue);
    List<Adresse> findByVille(@Param("VILLE") String ville);

    List<Adresse> findByCodePostal(@Param("CODE_POSTAL") String cp);

    List<Adresse> findByPays(@Param("PAYS") String pays);
}