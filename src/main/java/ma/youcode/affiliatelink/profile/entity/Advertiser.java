package ma.youcode.affiliatelink.profile.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "profile_advertisers")
@PrimaryKeyJoinColumn(name = "id")
public class Advertiser extends Profile {
    @Column(name = "company_name", nullable = false, unique = true)
    private String companyName;

    @Column(nullable = false)
    private String sector;

    @Column(nullable = false)
    private String website;
}
