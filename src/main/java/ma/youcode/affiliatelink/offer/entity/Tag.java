package ma.youcode.affiliatelink.offer.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tags", uniqueConstraints = @UniqueConstraint(name = "uk_tag_name", columnNames = "name"))
public class Tag {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 80)
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Offer> offers = new LinkedHashSet<>();

}
