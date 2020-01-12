package lab13.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Builder
public class AccountDto extends BaseDto {
    private int balance;
    private boolean isImportant;
}
