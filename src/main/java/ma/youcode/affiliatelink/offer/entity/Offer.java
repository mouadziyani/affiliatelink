package ma.youcode.affiliatelink.offer.entity;

import jakarta.persistence.*;
import lombok.*;
import ma.youcode.affiliatelink.offer.enums.OfferNiche;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long advertiserId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String landingUrl;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OfferNiche offerNiche;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @ManyToMany
    @JoinTable(name = "offer_tags",
            joinColumns = @JoinColumn(name = "offer_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    @Builder.Default
    private Set<Tag> tags = new LinkedHashSet<>();

    @OneToMany(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<Campaign> campaigns = new LinkedHashSet<>();

    public void addCampaign(Campaign campaign) {
        campaigns.add(campaign);
        campaign.setOffer(this);
    }
}
