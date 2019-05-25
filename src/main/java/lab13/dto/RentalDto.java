package lab13.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Builder
public class RentalDto extends BaseDto{
    private MovieDto movie;
    private ClientDto client;
    private int startMonth;
    private int startDate;
    private int endMonth;
    private int endDate;
}
