package ma.youcode.affiliatelink.offer.dto.response;

import ma.youcode.affiliatelink.offer.enums.CampaignStatus;
import ma.youcode.affiliatelink.offer.enums.PayoutModel;
import java.math.BigDecimal;
import java.time.LocalDate;

public record CampaignResDto(Long id, Long offerId, String name, LocalDate startDate, LocalDate endDate,
                             BigDecimal budget, PayoutModel payoutModel, BigDecimal payoutValue, CampaignStatus status) {}
