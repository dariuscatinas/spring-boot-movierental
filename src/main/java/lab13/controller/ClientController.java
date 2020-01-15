package lab13.controller;

import lab13.converter.AccountConverter;
import lab13.converter.ClientConverter;
import lab13.domain.Account;
import lab13.domain.Client;
import lab13.dto.AccountDto;
import lab13.dto.ClientDto;
import lab13.dto.ClientsDto;
import lab13.dto.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lab13.service.ClientServiceInt;
import org.springframework.web.context.request.WebRequest;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@ComponentScan(basePackages = {"lab13.service", "lab13.converter"})
@RestController
public class ClientController {

    private static final Logger log= LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientServiceInt clientService;

    @Autowired
    private ClientConverter clientConverter;

    @Autowired
    private AccountConverter accountConverter;

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    Set<ClientDto> getAllClients() {
        log.trace("getAllClients --- method entered");

        Set<Client> clients = clientService.getAllClients();
        Set<ClientDto> dtos = clientConverter.convertModelsToDtos(clients);

        log.trace("getAllClients: result={}", dtos);

        return dtos;
    }

    @RequestMapping(value="/clients",method = RequestMethod.POST)
    ClientDto saveClient(@RequestBody ClientDto clientDto){
        log.trace("saveClient: lab13.dto={}",clientDto);

        Optional<Client> saved=clientService.addClient(clientConverter.convertDtoToModel(clientDto));
        ClientDto result=clientConverter.convertModelToDto(saved.get());

        log.trace("saveClient: result={}",result);

        return result;
    }

    @RequestMapping(value="/clients/{id}",method = RequestMethod.PUT)
    ClientDto updateClient(@PathVariable Long id,@RequestBody ClientDto clientDto){
        log.trace("updateClient: id={} lab13.dto={}",id,clientDto);
        Optional<Client> updated=clientService.update(id,clientConverter.convertDtoToModel(clientDto));
        ClientDto result=clientConverter.convertModelToDto(updated.get());

        log.trace("updateClient: result={}",result);

        return result;
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> deleteClient(@PathVariable Long id) {
        log.trace("deleteClient: id={}", id);
        boolean deleted=false;
        Optional<Client> client=clientService.deleteClient(id);
        if (client.isPresent())
            deleted=true;
        log.trace("deleteClient --- method finished");
        if (deleted)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value="/clients/{id}",method = RequestMethod.GET)
    ClientDto findOneClient(@PathVariable Long id){
        log.trace("findOneClient: id={}",id);
        boolean found=false;
        ClientDto result=null;
        Optional<Client> client=clientService.findOne(id);
        if (client.isPresent())
            found=true;
        if (found)
            result=clientConverter.convertModelToDto(client.get());

        log.trace("findOneClient: lab13.dto={}",result);
        return result;
    }

    @RequestMapping(value="/clients/{clientId}",method = RequestMethod.POST)
    ClientDto addAccount(@PathVariable Long clientId, @RequestBody AccountDto accountDto){
        log.info("adding account: clientid={}", clientId);
        Account account = accountConverter.convertDtoToModel(accountDto);
        Optional<Client> clientOptional = clientService.addAccount(clientId, account);
        if (!clientOptional.isPresent()){
            throw new RuntimeException("Cannot perform operation.");
        }
        return clientConverter.convertModelToDto(clientOptional.get());

    }

    @RequestMapping(value="/accounts", method = RequestMethod.GET)
    Set<AccountDto> getAllAccounts(){
        log.info("Getting accounts... controller");
        return accountConverter.convertModelsToDtos(clientService.getAllAccounts());
    }


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        ErrorResponse error = ErrorResponse.builder().message(details.get(0)).status(500).build();
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
