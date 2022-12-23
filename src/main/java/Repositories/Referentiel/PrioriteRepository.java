package Repositories.Referentiel;


import Models.*;
import org.springframework.stereotype.Repository;


import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface PrioriteRepository extends PagingAndSortingRepository<Priorite, Long>, CrudRepository<Priorite,Long> {

    List<Priorite> findByName(@Param("NOM") String name);

}