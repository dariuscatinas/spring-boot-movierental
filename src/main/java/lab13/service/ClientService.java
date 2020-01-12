package lab13.service;

import lab13.domain.Account;
import lab13.domain.Client;
import lab13.repository.AccountRepository;
import lab13.repository.n.plusone.repository.ClientGraphRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import lab13.repository.ClientRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class ClientService implements ClientServiceInt {

    private static final Logger log = LoggerFactory.getLogger(
            ClientService.class);


    @Autowired
    private ClientRepository repository;

    @Autowired
    private AccountRepository accountRepository;


    /**
     * Adds a {@code Client} to the lab13.repository
     * @param client, the Client to add to the lab13.repository
     */
    public Optional<Client> addClient(Client client) {

        Assert.isTrue(client.getAge() > 0, "Client age has to be > 0");
        Assert.isTrue(client.getName().length() > 0, "Client name has got to be not empty");
        Assert.isTrue(client.getEmail().contains("@"), "Client's email has to be of valid format");
        log.trace("add client client={}", client);
        Optional<Client> isPresent = repository.findClientByName(client.getName());
        Assert.isTrue(!isPresent.isPresent(), "Client with name already present");
        Optional<Client> added= Optional.of(repository.save(client));
        log.trace("method finished ---");
        return added;
    }

    /**
     * Deletes a {@code Client} from the lab13.repository
     * @param id, representing the identification of a Client
     * @return true if the Client was found and deleted, false otherwise
     */

    public Optional<Client> deleteClient(Long id) {

        log.trace("delete client id={}", id);
        Optional<Client> entity=repository.findById(id);
        entity.ifPresent(e->repository.deleteById(id));
        log.trace("method finished---");
        return entity;
    }

    /**
     * @return an {@code Set<Client>} of the entire collection of Clients.
     */
    public Set<Client> getAllClients(){
        log.trace("getAllClients ---");
        Set<Client> clients= StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toSet());
        log.trace("result={}",clients);
        return clients;
    }


    /**
     * Updates an existing client
     * @param newClient, the client with the new information of the client
     * @return an {@code Optional} which contains the previous movie information
     */
    @Transactional
    public Optional<Client> update(long id,Client newClient){
        log.trace("updateClient newClient={}",newClient);
        Optional<Client> dbClient=repository.findById(id);
        Client result=dbClient.orElse(newClient);
        result.setName(newClient.getName());
        result.setEmail(newClient.getEmail());
        result.setAge(newClient.getAge());
        log.trace("method finished ---");
        return Optional.of(result);
    }

    /**
     * Retrieves a client by its id
     * @param cnp, the clients id
     * @return an {@code Optional} containing the client with the given id or null if it does not exist
     */
    public Optional<Client> findOne(long cnp){
        log.trace("findClient id={}",cnp);
        Optional<Client> client= repository.findById(cnp);
        log.trace("method finished ---");
        return client;
    }
    @Transactional
    public Optional<Client> addAccount(long clientId, Account newAccount){
        log.info("adding.... ");
        Optional<Client> clientOptional = repository.findById(clientId);
        Assert.isTrue(clientOptional.isPresent(), "Client not found. Try again!");
        Client client = clientOptional.get();
        newAccount.setClient(client);
        client.getAccounts().add(newAccount);
        accountRepository.save(newAccount);
        return Optional.of(client);

    }

}
