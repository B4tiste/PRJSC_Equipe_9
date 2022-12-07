package Repositories;

import Models.Urgence;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public abstract class BaseRepository implements CrudRepository {

}
