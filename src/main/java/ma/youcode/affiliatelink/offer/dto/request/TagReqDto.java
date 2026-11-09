package ma.youcode.affiliatelink.offer.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TagReqDto(@NotBlank @Size(max = 80) String name) {}
