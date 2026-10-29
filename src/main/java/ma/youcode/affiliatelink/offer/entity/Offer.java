package ma.youcode.affiliatelink.offer.entity;

import jakarta.persistence.*;
import lombok.*;
import ma.youcode.affiliatelink.offer.enums.OfferNiche;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
@Getter
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
}
