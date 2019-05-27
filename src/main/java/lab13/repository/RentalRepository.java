package lab13.repository;

import lab13.domain.NewRental;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface RentalRepository extends JPARepository<NewRental, Long> {
}
