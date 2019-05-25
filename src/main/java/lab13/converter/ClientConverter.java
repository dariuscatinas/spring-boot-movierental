package lab13.converter;

import lab13.domain.Client;
import lab13.dto.ClientDto;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter extends BaseConverter<Client,ClientDto> {

    @Override
    public Client convertDtoToModel(ClientDto dto) {
        Client client=Client.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .age(dto.getAge())
                .build();
        client.setId(dto.getId());
        return client;
    }

    @Override
    public ClientDto convertModelToDto(Client client) {
        ClientDto clientDto=ClientDto.builder()
                .name(client.getName())
                .age(client.getAge())
                .email(client.getEmail())
                .build();
        clientDto.setId(client.getId());
        return clientDto;
    }
}
