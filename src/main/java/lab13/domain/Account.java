package lab13.domain;


import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity(name = "Account")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@Table(name="account")
public class Account extends BaseEntity<Long>{

    private int balance;
    private boolean isImportant;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="cId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    Client client;
}
