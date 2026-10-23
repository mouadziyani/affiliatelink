package ma.youcode.affiliatelink.profile.dto.response;

import lombok.Builder;
import ma.youcode.affiliatelink.profile.enums.ProfileStatue;

import java.time.Instant;

@Builder
public record ProfileResDto(
    Long id,
    String email,
    ProfileStatue statue,
    Instant createdAt
) {}
