package lab13.dto;

import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Builder
public class ClientDto extends BaseDto{

    private String name;
    private String email;
    private int age;
    private List<AccountDto> accounts;
}
