package lab13.service;

import lab13.domain.NewRental;
import org.springframework.context.annotation.ComponentScan;
import lab13.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@ComponentScan(basePackages = {"lab13.repository"})
@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    private RentalRepository repo;

    @Override
    public Set<NewRental> getAll() {
        return new HashSet<>(repo.findAll());
    }

    @Override
    public Optional<NewRental> add(NewRental elem) {
        return Optional.of(repo.save(elem));
    }

    @Override
    @Transactional
    public Optional<NewRental> getOne(Long id){
            NewRental r = repo.getOne(id);
            if( r != null){
                System.out.println(r);
            }
            return Optional.ofNullable(r);}

    @Override

    public Optional<NewRental> update(Long id, NewRental newRental) {
        if (repo.getOne(id) == null)
            return Optional.empty();
        repo.deleteById(id);
        repo.save(newRental);
        return Optional.ofNullable(newRental);

    }

    @Override
    public Optional<NewRental> delete(Long id) {
        NewRental r;
        if( (r = repo.getOne(id)) == null){
            return Optional.empty();
        }
        repo.deleteById(id);
        return Optional.of(r);
    }
}
