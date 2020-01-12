package lab13.converter;

import lab13.domain.Account;
import lab13.domain.Client;
import lab13.dto.AccountDto;
import lab13.dto.ClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientConverter extends BaseConverter<Client,ClientDto> {

    @Autowired
    private AccountConverter accountConverter;

    @Override
    public Client convertDtoToModel(ClientDto dto) {
        List<Account> accounts = dto.getAccounts().stream().map(accDto -> accountConverter.convertDtoToModel(accDto)).collect(Collectors.toList());
        Client client=Client.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .age(dto.getAge())
                .accounts(accounts)
                .build();
        client.setId(dto.getId());
        return client;
    }

    @Override
    public ClientDto convertModelToDto(Client client) {
        List<AccountDto> accountDtos = client.getAccounts().stream().map(acc -> accountConverter.convertModelToDto(acc)).collect(Collectors.toList());
        ClientDto clientDto=ClientDto.builder()
                .name(client.getName())
                .age(client.getAge())
                .email(client.getEmail())
                .accounts(accountDtos)
                .build();
        clientDto.setId(client.getId());
        return clientDto;
    }
}
