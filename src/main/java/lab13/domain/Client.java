package lab13.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NamedEntityGraphs({
        @NamedEntityGraph(name = "clientWithAccounts",
                attributeNodes = @NamedAttributeNode(value = "accounts")),


//        @NamedEntityGraph(name = "movieWithRentalsAndCl",
//                attributeNodes = @NamedAttributeNode(value = "books",
//                        subgraph = "bookWithPublisher"),
//                subgraphs = @NamedSubgraph(name = "bookWithPublisher",
//                        attributeNodes = @NamedAttributeNode(value =
//                                "publisher")))
})


@Entity(name="Client")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@Table(name="client")
public class Client extends BaseEntity<Long> {

    private String name;
    private String email;
    private int age;
    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.EAGER, mappedBy = "client", orphanRemoval = true)
    List<Account> accounts;

}
