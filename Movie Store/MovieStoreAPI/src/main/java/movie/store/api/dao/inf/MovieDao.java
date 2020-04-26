package movie.store.api.dao.inf;

import java.util.List;

import movie.store.api.models.dao.MovieBean;
import movie.store.api.models.dto.MovieSearchCriteriaBean;

public interface MovieDao{

	List<MovieBean> findByCriteria(MovieSearchCriteriaBean movieCriteriaBean);

}
