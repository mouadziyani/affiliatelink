package ma.youcode.affiliatelink.profile.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import ma.youcode.affiliatelink.profile.enums.ProfileStatue;

@Builder
public record ProfileReqDto(
        @NotNull(message = "Status is required")
        ProfileStatue statue
        ) {}
