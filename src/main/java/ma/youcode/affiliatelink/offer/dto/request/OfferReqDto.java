package ma.youcode.affiliatelink.offer.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import ma.youcode.affiliatelink.offer.enums.OfferNiche;

import java.util.List;

public record OfferReqDto(
        @NotNull @Positive Long advertiserId,
        @NotBlank @Size(max = 100) String title,
        @NotBlank @Size(max = 2000) String description,
        @NotBlank @Size(max = 2048) String landingUrl,
        @NotNull OfferNiche offerNiche,
        List<@NotBlank @Size(max = 80) String> tags
) {}
