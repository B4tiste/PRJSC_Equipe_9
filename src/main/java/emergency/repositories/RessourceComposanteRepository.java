package emergency.repositories;

import emergency.models.Personne;
import emergency.models.RessourceComposante;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RessourceComposanteRepository extends PagingAndSortingRepository<RessourceComposante, Long>, CrudRepository<RessourceComposante, Long> {

    List<RessourceComposante> findByNom(@Param("NOM") String nom);

    List<RessourceComposante> findByRessource(@Param("ID_RESSOURCE") Long idRessource);

}