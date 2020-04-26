package movie.store.api.dao.inf;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import movie.store.api.models.dao.DirectorBean;

public interface DirectorDao extends CrudRepository<DirectorBean, Integer> {

	List<DirectorBean> findAll();

}
