package lab13.service;

import lab13.domain.NewRental;

import java.util.Optional;
import java.util.Set;

public interface RentalService {

    Set<NewRental> getAll();
    Optional<NewRental> add(NewRental elem);
    Optional<NewRental> getOne(Long id);
    Optional<NewRental> update(Long id, NewRental newRental);
    Optional<NewRental> delete(Long id);
}
