package movie.store.api.service.inf;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import movie.store.api.models.dao.DirectorBean;
import movie.store.api.models.dao.GenreBean;
import movie.store.api.models.dao.MovieBean;
import movie.store.api.models.dto.MovieSearchCriteriaBean;

public interface MovieStoreServiceInf {
	public CompletableFuture<List<GenreBean>> getGenres();
	public CompletableFuture<List<DirectorBean>> getDirectors();
	public CompletableFuture<List<MovieBean>> getMoviesByCriteria(MovieSearchCriteriaBean searchCriteria);
}
