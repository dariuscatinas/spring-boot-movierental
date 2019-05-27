package lab13.repository.n.plusone.repository;

import lab13.domain.Client;
import lab13.domain.Movie;
import lab13.repository.JPARepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieGraphRepository extends JPARepository<Movie, Long> {
    @Query("select distinct m from Movie m")
    @EntityGraph(value = "movieWithRentals", type =
            EntityGraph.EntityGraphType.LOAD)
    List<Client> findAllWithRentals();

}