package lab13.repository.n.plusone.repository;

import lab13.domain.Client;
import lab13.repository.JPARepository;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientGraphRepository extends JPARepository< Client, Long>, ClientJPQLRepository{
    @Query("select distinct c from Client c")
    @EntityGraph(value = "clientWithRentals", type =
            EntityGraph.EntityGraphType.LOAD)
    List<Client> findAllWithRentals();

}
