package ma.youcode.affiliatelink.profile.dto.request;

import jakarta.validation.constraints.*;
import ma.youcode.affiliatelink.profile.enums.PublisherNiche;

public record AdvertiserReqDto(
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid ")
        @Size(max = 255 , message = "Email must not exceed 100 characters")
        String email,

        @NotBlank(message = "Company name is required")
        @Size(min = 2, max = 100, message = "Company Name name must not exceed 100 characters")
        String companyName,

        @NotBlank(message = "Last name is required")
        @Size(max = 100, message = "sector must not exceed 100 characters")
        String sector,

        @Size(max = 100, message = "website must not exceed 100 characters")
        PublisherNiche website
) {
}
