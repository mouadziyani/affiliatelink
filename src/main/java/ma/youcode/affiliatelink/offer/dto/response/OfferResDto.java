
package ma.youcode.affiliatelink.offer.dto.response;

import ma.youcode.affiliatelink.offer.enums.OfferNiche;

import java.time.Instant;

import lombok.Builder;

@Builder 
public record OfferResDto(
        Long id,
        Long advertiserId,
        String title,
        String description,
        String landingUrl,
        OfferNiche offerNiche,
        Instant createdAt
) {
}
