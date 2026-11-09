package ma.youcode.affiliatelink.offer.entity;

import jakarta.persistence.*;
import lombok.*;
import ma.youcode.affiliatelink.offer.enums.CampaignStatus;
import ma.youcode.affiliatelink.offer.enums.PayoutModel;
import ma.youcode.affiliatelink.shared.exception.ConflictException;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter @NoArgsConstructor @Entity @Table(name = "campaigns")
public class Campaign {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 120)
    private String name;
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal budget;
    @Enumerated(EnumType.STRING) @Column(name = "payout_model", nullable = false)
    private PayoutModel payoutModel;
    @Column(name = "payout_value", nullable = false, precision = 12, scale = 2)
    private BigDecimal payoutValue;
    @Enumerated(EnumType.STRING) @Column(nullable = false, length = 20)
    private CampaignStatus status = CampaignStatus.DRAFT;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;

    @Builder
    public Campaign(String name, LocalDate startDate, LocalDate endDate, BigDecimal budget,
                    PayoutModel payoutModel, BigDecimal payoutValue) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.payoutModel = payoutModel;
        this.payoutValue = payoutValue;
    }

    public void setOffer(Offer offer) { this.offer = offer; }

    public void transitionTo(CampaignStatus next) {
        boolean allowed = (status == CampaignStatus.DRAFT && next == CampaignStatus.ACTIVE)
                || (status == CampaignStatus.ACTIVE && (next == CampaignStatus.PAUSED || next == CampaignStatus.ENDED))
                || (status == CampaignStatus.PAUSED && (next == CampaignStatus.ACTIVE || next == CampaignStatus.ENDED));
        if (!allowed) throw new ConflictException("Campaign transition from " + status + " to " + next + " is not allowed");
        if (next == CampaignStatus.ACTIVE && endDate.isBefore(LocalDate.now()))
            throw new ConflictException("Expired campaign cannot be activated");
        status = next;
    }

    public void pauseForAdvertiserSuspension() {
        if (status == CampaignStatus.ACTIVE) status = CampaignStatus.PAUSED;
    }
}
