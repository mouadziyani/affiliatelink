package ma.youcode.affiliatelink.profile.dto.response;

import lombok.Builder;
import ma.youcode.affiliatelink.profile.enums.ProfileStatue;
import ma.youcode.affiliatelink.profile.enums.PublisherNiche;

import java.time.Instant;

@Builder
public record PublisherResDto(
        Long id,
        String email,
        String firstName,
        String lastName,
        PublisherNiche niche,
        String bio,
        String country,
        ProfileStatue statue,
        Instant createdAt
) {}
