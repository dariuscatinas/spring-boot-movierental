package lab13.domain;

import lab13.repository.utils.LocalDateConverter;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "Rental")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "rental")
@Transactional
public class NewRental extends BaseEntity<Long>{

    private static final int BASEPRICE=10;

//    @Id
//    @GeneratedValue
//    Long id;

    @Column
    @Convert(converter=LocalDateConverter.class)
    LocalDate startDate;

    @Column
    @Convert(converter=LocalDateConverter.class)
    LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    Client client;


}
