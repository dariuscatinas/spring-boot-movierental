package lab13.repository;

import lab13.domain.NewRental;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JPARepository<NewRental, Long> {
}
