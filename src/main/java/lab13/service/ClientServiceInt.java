package lab13.service;

import lab13.domain.Client;

import java.util.Optional;
import java.util.Set;

public interface ClientServiceInt extends Service<Long,Client>{

    Optional<Client> addClient(Client client);
    Optional<Client> deleteClient(Long id);
    Set<Client> getAllClients();
    Optional<Client> update(long id,Client newClient);
    Optional<Client> findOne(long cnp);
}
