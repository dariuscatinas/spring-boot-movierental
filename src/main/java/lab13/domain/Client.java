package lab13.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@NamedEntityGraphs({
        @NamedEntityGraph(name = "clientWithRentals",
                attributeNodes = @NamedAttributeNode(value = "rentals")),


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
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY, mappedBy = "client", orphanRemoval = true)
    List<NewRental> rentals;


}
