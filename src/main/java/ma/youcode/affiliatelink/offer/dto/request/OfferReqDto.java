
package ma.youcode.affiliatelink.offer.dto.request;

import jakarta.validation.constraints.*;
import lombok.Builder;
import ma.youcode.affiliatelink.offer.enums.OfferNiche;

@Builder
public record OfferReqDto(
        @NotNull(message = "Advertiser ID is required")
        @Positive(message = "Advertiser ID must be positive")
        Long advertiserId,

        @NotBlank(message = "Title is required")
        @Size(max = 100, message = "Title must not exceed 100 characters")
        String title,

        @NotBlank(message = "Description is required")
        @Size(max = 2000, message = "Description must not exceed 2000 characters")
        String description,

        @NotBlank(message = "Landing URL is required")
        @Size(max = 2048, message = "Landing URL must not exceed 2048 characters")
        String landingUrl,

        @NotNull(message = "Offer niche is required")
        OfferNiche offerNiche
) {
}
