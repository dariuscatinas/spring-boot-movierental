package lab13.repository;

import lab13.domain.Account;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JPARepository<Account, Long> {
}
