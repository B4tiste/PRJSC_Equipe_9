package Repositories.Referentiel;

import Models.Priorite;
import Models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Long>, CrudRepository<Role,Long> {

    List<Role> findByName(@Param("NOM") String name);

}