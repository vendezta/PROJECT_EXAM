package movie.store.api.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import movie.store.api.dao.inf.MovieDao;
import movie.store.api.models.dao.DirectorBean;
import movie.store.api.models.dao.GenreBean;
import movie.store.api.models.dao.MovieBean;
import movie.store.api.models.dto.MovieSearchCriteriaBean;

@Repository
public class MovieDaoImp implements MovieDao  {
	
	@PersistenceContext
	private EntityManager entityManager;
	 
    @Override
    public List<MovieBean> findByCriteria(MovieSearchCriteriaBean movieCriteriaBean) {
    	
    	CriteriaBuilder cb = entityManager.getCriteriaBuilder();
    	
        CriteriaQuery<MovieBean> query = cb.createQuery(MovieBean.class);
        Root<MovieBean> root = query.from(MovieBean.class);
        
        List<Predicate> predicates = new ArrayList<>();
        
        if(movieCriteriaBean.getDirectorId() != null) {
            Join<MovieBean, DirectorBean> joinDirectorBean = root.join("directorBean");
            predicates.add(cb.and(cb.equal(joinDirectorBean.get("id"), movieCriteriaBean.getDirectorId())));
        }
        
        if(movieCriteriaBean.getRating() != null) {
        	predicates.add(cb.and(cb.equal(root.get("rating"), movieCriteriaBean.getRating())));
        }
        
        if(movieCriteriaBean.getGenreId() != null) {
        	Join<MovieBean, GenreBean> joinGenreBean = root.join("genreBean");
            predicates.add(cb.and(cb.equal(joinGenreBean.get("id"), movieCriteriaBean.getGenreId())));
        }
        
        if(movieCriteriaBean.getTitle() != null) {
            predicates.add(cb.and(cb.like(root.get("title"), "%"+movieCriteriaBean.getTitle()+"%")));
        }
        
        query.select(root).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));

        return entityManager.createQuery(query)
                .getResultList();
    }

}
