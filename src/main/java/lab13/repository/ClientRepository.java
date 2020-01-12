package lab13.repository;

import lab13.domain.Client;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JPARepository<Client, Long> {
    Optional<Client> findClientByName(String name);

    @EntityGraph(value = "clientWithAccounts")
    Optional<Client> findById(Long ID);
}
