package ma.youcode.affiliatelink.profile.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ma.youcode.affiliatelink.profile.enums.PublisherNiche;

@Getter
@Setter
@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "profile_publishers")
@PrimaryKeyJoinColumn(name = "id")
public class Publisher extends Profile {
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PublisherNiche niche;

    @Column(nullable = false)
    private String bio;

    @Column(nullable = false)
    private String country;

    public String getUserFullName() {
        return firstName + " " + lastName;
    }
}