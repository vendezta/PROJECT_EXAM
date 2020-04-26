package movie.store.api.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import movie.store.api.dao.inf.DirectorDao;
import movie.store.api.dao.inf.GenreDao;
import movie.store.api.dao.inf.MovieDao;
import movie.store.api.models.dao.DirectorBean;
import movie.store.api.models.dao.GenreBean;
import movie.store.api.models.dao.MovieBean;
import movie.store.api.models.dto.MovieSearchCriteriaBean;
import movie.store.api.service.inf.MovieStoreServiceInf;

@Service
public class MovieStoreServiceImp implements MovieStoreServiceInf {

	private static final Logger LOGGER = LoggerFactory.getLogger(MovieStoreServiceImp.class);

	private GenreDao genreDao;
	private DirectorDao directorDao;
	private MovieDao movieDao;
	
	@Autowired
	MovieStoreServiceImp(GenreDao genreDao, DirectorDao directorDao, MovieDao movieDao) {
		this.directorDao = directorDao;
		this.genreDao = genreDao;
		this.movieDao = movieDao;
	}
	
	@Async
	@Cacheable("genres")
    public CompletableFuture<List<GenreBean>> getGenres() {
        LOGGER.info("Get Genres Service. ");
        
        final List<GenreBean> genres = genreDao.findAll();
        return CompletableFuture.completedFuture(genres);
    }

	@Async
	public CompletableFuture<List<DirectorBean>> getDirectors() {
		 LOGGER.info("Get Directors Service. ");
		 
        final List<DirectorBean> directors = directorDao.findAll();
        return CompletableFuture.completedFuture(directors);
	}

	@Override
	public CompletableFuture<List<MovieBean>> getMoviesByCriteria(MovieSearchCriteriaBean searchCriteria) {
		 LOGGER.info("Get MoviesByCriteria Service. ");
		 
        final List<MovieBean> movies = movieDao.findByCriteria(searchCriteria);
        return CompletableFuture.completedFuture(movies);
	}
}
