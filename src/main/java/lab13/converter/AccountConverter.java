package lab13.converter;

import lab13.domain.Account;
import lab13.dto.AccountDto;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter extends BaseConverter<Account, AccountDto> {

    @Override
    public Account convertDtoToModel(AccountDto dto) {
        return Account.builder().balance(dto.getBalance()).isImportant(dto.isImportant()).build();
    }

    @Override
    public AccountDto convertModelToDto(Account account) {
        AccountDto dto =  AccountDto.builder().balance(account.getBalance()).isImportant(account.isImportant()).build();
        dto.setId(account.getId());
        return dto;
    }
}
