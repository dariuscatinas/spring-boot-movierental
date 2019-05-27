package lab13.service;

import lab13.domain.Client;
import lab13.domain.Movie;
import lab13.domain.NewRental;
import lab13.repository.n.plusone.repository.ClientGraphRepository;
import lab13.repository.n.plusone.repository.MovieGraphRepository;
import org.springframework.context.annotation.ComponentScan;
import lab13.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ComponentScan(basePackages = {"lab13.repository"})
@Service
public class RentalServiceImpl implements RentalService {

    @Autowired
    private ClientGraphRepository clientRepository;


    @Override
    public Set<NewRental> getAll() {
        Set<NewRental> set = clientRepository.findAllWithRentals().stream().map(Client::getRentals).flatMap(List::stream).collect(Collectors.toSet());
        set.forEach(System.out::println);
        return set;
     }

    @Override
    public Optional<NewRental> add(NewRental elem) {

        Long cid = elem.getClient().getId();
        Long mid = elem.getMovie().getId();
        Client client = clientRepository.getOne(cid);
        client.getRentals().add(elem);
        clientRepository.save(client);
        return Optional.of(elem);

    }

    @Override
    @Transactional
    public Optional<NewRental> getOne(Long id){
        List<Client> all = clientRepository.findAllWithRentals();
        for(Client c : all){
            for (NewRental r : c.getRentals()){
                if (r.getId().equals(id)){
                    return Optional.of(r);
                }
            }
        }
        return Optional.empty();

    }

    @Override

    public Optional<NewRental> update(Long id, NewRental newRental) {
        Optional<NewRental> found = getOne(id);
        if(!found.isPresent())
            return Optional.empty();
        NewRental rental = found.get();
        Client c = rental.getClient();
        rental .setStartDate(newRental.getStartDate());
        rental .setEndDate(newRental.getEndDate());
        clientRepository.save(c);
        return found;
    }

    @Override
    public Optional<NewRental> delete(Long id) {
        Optional<NewRental> found = getOne(id);
        if (!found.isPresent()){
            return Optional.empty();
        }
        NewRental rental = found.get();
        Client c = rental.getClient();
        c.getRentals().remove(rental);
        clientRepository.save(c);
        return found;
    }
}
