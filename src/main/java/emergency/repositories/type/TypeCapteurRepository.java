package emergency.repositories.type;

import emergency.models.sensorRelated.CapteurType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Component
public interface TypeCapteurRepository extends PagingAndSortingRepository<CapteurType, Long>, CrudRepository<CapteurType, Long> {

    List<CapteurType> findByType(@Param("TYPE") String type);
}