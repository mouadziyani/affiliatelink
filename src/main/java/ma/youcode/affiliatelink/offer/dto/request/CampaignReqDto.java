package ma.youcode.affiliatelink.offer.dto.request;

import jakarta.validation.constraints.*;
import ma.youcode.affiliatelink.offer.enums.PayoutModel;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CampaignReqDto(
        @NotBlank @Size(max = 120) String name,
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate,
        @NotNull @DecimalMin(value = "0.01") BigDecimal budget,
        @NotNull PayoutModel payoutModel,
        @NotNull @DecimalMin(value = "0.00") BigDecimal payoutValue) {
}
