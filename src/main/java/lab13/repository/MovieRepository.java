package lab13.repository;

import lab13.domain.Movie;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface MovieRepository extends JPARepository<Movie,Long> {
}
