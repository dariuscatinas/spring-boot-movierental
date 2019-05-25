package lab13.repository;

import lab13.domain.Movie;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JPARepository<Movie,Long> {
}
