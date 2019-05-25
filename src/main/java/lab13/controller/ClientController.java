package lab13.controller;

import lab13.converter.ClientConverter;
import lab13.domain.Client;
import lab13.dto.ClientDto;
import lab13.dto.ClientsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lab13.service.ClientServiceInt;


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

        log.trace("updateClient: resul={}",result);

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

    @RequestMapping(value="clients/filterG/{age}", method = RequestMethod.GET)
    ClientsDto filterClientsG(@PathVariable int age){

        return new ClientsDto(clientConverter.convertModelsToDtos(
                clientService.getAllClients().stream().filter(c->c.getAge()>age).collect(Collectors.toSet())));
    }

    @RequestMapping(value="clients/filterL/{age}", method = RequestMethod.GET)
    ClientsDto filterClientsL(@PathVariable int age){

        return new ClientsDto(clientConverter.convertModelsToDtos(
                clientService.getAllClients().stream().filter(c->c.getAge()<age).collect(Collectors.toSet())));
    }

    @RequestMapping(value="clients/filter",method = RequestMethod.GET)
    ClientsDto filterClients(@RequestParam("age") String age) {
        return new ClientsDto(clientConverter.convertModelsToDtos(
                clientService.getAllClients().stream().filter(c->c.getAge()<Integer.parseInt(age)).collect(Collectors.toSet())));

    }
}
