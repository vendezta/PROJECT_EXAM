package movie.store.api.dao.inf;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import movie.store.api.models.dao.DirectorBean;

@Repository
public interface DirectorDao extends CrudRepository<DirectorBean, Integer> {

	List<DirectorBean> findAll();

}
