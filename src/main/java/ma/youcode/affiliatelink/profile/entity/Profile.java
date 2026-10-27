package ma.youcode.affiliatelink.profile.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ma.youcode.affiliatelink.profile.enums.ProfileStatue;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Getter
@SuperBuilder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "profile_profiles")
public abstract class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private ProfileStatue status = ProfileStatue.PENDING;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    public void changeStatus(ProfileStatue newStatus) {
        boolean allowed = (status == ProfileStatue.PENDING && newStatus == ProfileStatue.ACTIVE)
                || (status == ProfileStatue.ACTIVE && newStatus == ProfileStatue.SUSPENDED)
                || (status == ProfileStatue.SUSPENDED && newStatus == ProfileStatue.ACTIVE);

        if (!allowed) {
            throw new IllegalStateException(
                    "Cannot change profile status from " + status + " to " + newStatus);
        }
        this.status = newStatus;
    }
}
