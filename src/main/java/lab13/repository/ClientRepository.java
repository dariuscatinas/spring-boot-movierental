package lab13.repository;

import lab13.domain.Client;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface ClientRepository extends JPARepository<Client, Long> {
}
