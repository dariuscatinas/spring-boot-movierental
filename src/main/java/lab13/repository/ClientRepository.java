package lab13.repository;

import lab13.domain.Client;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JPARepository<Client, Long> {
}
