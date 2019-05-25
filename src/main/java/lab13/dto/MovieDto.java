package lab13.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString(callSuper = true)
@Builder
public class MovieDto extends BaseDto {
    private String title;
    private String genre;
    private float rating;
}
