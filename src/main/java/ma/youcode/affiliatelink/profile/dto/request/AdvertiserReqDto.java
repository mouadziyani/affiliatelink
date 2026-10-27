package ma.youcode.affiliatelink.profile.dto.request;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.URL;

public record AdvertiserReqDto(
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid ")
        @Size(max = 255, message = "Email must not exceed 255 characters")
        String email,

        @NotBlank(message = "Company name is required")
        @Size(min = 2, max = 255, message = "Company name must be between 2 and 255 characters")
        String companyName,

        @NotBlank(message = "Sector is required")
        @Size(max = 255, message = "Sector must not exceed 255 characters")
        String sector,

        @NotBlank(message = "Website is required")
        @URL(message = "Website must be a valid URL")
        @Size(max = 255, message = "Website must not exceed 255 characters")
        String website
) {
}
