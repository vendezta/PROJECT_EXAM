package movie.store.api.dao.inf;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import movie.store.api.models.dao.GenreBean;

@Repository
public interface GenreDao extends JpaRepository<GenreBean, Integer> {

	List<GenreBean> findAll();
}
