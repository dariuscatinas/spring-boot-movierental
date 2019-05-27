package lab13.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@NamedEntityGraphs({
        @NamedEntityGraph(name = "movieWithRentals",
                attributeNodes = @NamedAttributeNode(value = "rentals")),


//        @NamedEntityGraph(name = "movieWithRentalsAndCl",
//                attributeNodes = @NamedAttributeNode(value = "books",
//                        subgraph = "bookWithPublisher"),
//                subgraphs = @NamedSubgraph(name = "bookWithPublisher",
//                        attributeNodes = @NamedAttributeNode(value =
//                                "publisher")))
})

@Entity(name = "Movie")
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@Table(name = "movie")
public class Movie extends BaseEntity<Long> {

    private String title;
    private String genre;
    private float rating;
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY , mappedBy = "movie")
    List<NewRental> rentals;


}
