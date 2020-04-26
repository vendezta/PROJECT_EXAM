package movie.store.api.controller;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import movie.store.api.models.dao.MovieBean;
import movie.store.api.models.dto.MovieSearchCriteriaBean;
import movie.store.api.service.inf.MovieStoreServiceInf;

@RestController
@RequestMapping("/api/moviestore")
public class MovieStoresApiController {

	private static final Logger logger = LoggerFactory.getLogger(MovieStoresApiController.class);

    @Autowired
    private MovieStoreServiceInf movieStoreServiceInf;

    @SuppressWarnings("rawtypes")
	@ApiOperation(value = "Get Movies", notes = "Get some movies information by criteria.")
    @RequestMapping ( value = "/movies", method = RequestMethod.GET, consumes={MediaType.APPLICATION_JSON_VALUE},
            produces={MediaType.APPLICATION_JSON_VALUE} )
    public @ResponseBody CompletableFuture<ResponseEntity> getMovieByCriteria(MovieSearchCriteriaBean searchCriteria) {
    	logger.debug("Get Directors Controller. "+searchCriteria.getTitle());
    	
        return movieStoreServiceInf.getMoviesByCriteria(searchCriteria).<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(handleGetCarFailure);
    }
    
    @SuppressWarnings("rawtypes")
	@ApiOperation(value = "Get Genres", notes = "Get all movie genres information.")
	@RequestMapping ( value = "/genres", method = RequestMethod.GET, consumes={MediaType.APPLICATION_JSON_VALUE},
            produces={MediaType.APPLICATION_JSON_VALUE} )
    public @ResponseBody CompletableFuture<ResponseEntity> getGenres() {
    	logger.debug("Get Genres Controller.");
    	
        return movieStoreServiceInf.getGenres().<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(handleGetCarFailure);
    }
	
    @SuppressWarnings("rawtypes")
	@ApiOperation(value = "Get Directors", notes = "Get all directors information.")
	@RequestMapping ( value = "/directors", method = RequestMethod.GET, consumes={MediaType.APPLICATION_JSON_VALUE},
            produces={MediaType.APPLICATION_JSON_VALUE} )
    public @ResponseBody CompletableFuture<ResponseEntity> getDirectors() {
    	logger.debug("Get Directors Controller.");
    	
        return movieStoreServiceInf.getDirectors().<ResponseEntity>thenApply(ResponseEntity::ok)
                .exceptionally(handleGetCarFailure);
    }

    private static Function<Throwable, ResponseEntity<? extends List<MovieBean>>> handleGetCarFailure = throwable -> {
    	logger.error("Failed to get data.", throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };
    
}
