package ma.youcode.affiliatelink.profile.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import ma.youcode.affiliatelink.profile.enums.PublisherNiche;

public record PublisherReqDto (
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid ")
        @Size(max = 255 , message = "Email must not exceed 100 characters")
        String email,

        @NotBlank(message = "First name is required")
        @Size(max = 100, message = "First name must not exceed 100 characters")
        String firstName,

        @NotBlank(message = "Last name is required")
        @Size(max = 100, message = "Last name must not exceed 100 characters")
        String lastName,

        @NotNull(message = "Niche is required")
        @Size(max = 100, message = "Niche must not exceed 100 characters")
        PublisherNiche niche,

        @NotBlank(message = "Message is required")
        @Size(max = 100, message = "Message must not exceed 100 characters")
        String bio ,

        @NotBlank(message = "Country is required")
        @Size(max = 100, message = "Country must not exceed 100 characters")
        String country
){}
