package ma.youcode.affiliatelink.offer.service;

import lombok.RequiredArgsConstructor;
import ma.youcode.affiliatelink.offer.dto.request.CampaignReqDto;
import ma.youcode.affiliatelink.offer.dto.response.CampaignResDto;
import ma.youcode.affiliatelink.offer.entity.Campaign;
import ma.youcode.affiliatelink.offer.entity.Offer;
import ma.youcode.affiliatelink.offer.enums.CampaignStatus;
import ma.youcode.affiliatelink.offer.enums.PayoutModel;
import ma.youcode.affiliatelink.offer.repository.CampaignRepository;
import ma.youcode.affiliatelink.offer.repository.OfferRepository;
import ma.youcode.affiliatelink.profile.AdvertiserApi;
import ma.youcode.affiliatelink.shared.exception.ConflictException;
import ma.youcode.affiliatelink.shared.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CampaignServices {
    private final CampaignRepository campaignRepository;
    private final OfferRepository offerRepository;
    private final AdvertiserApi advertiserApi;

    @Transactional
    public CampaignResDto create(Long offerId, CampaignReqDto dto) {
        Offer offer = offerRepository.findById(offerId).orElseThrow(() -> new ResourceNotFoundException("Offer not found"));
        if (!advertiserApi.existsById(offer.getAdvertiserId()))
            throw new ResourceNotFoundException("Advertiser not found");
        if (!advertiserApi.isActive(offer.getAdvertiserId()))
            throw new ConflictException("Advertiser account must be active");
        if (!dto.endDate().isAfter(dto.startDate()))
            throw new ConflictException("Campaign end date must be after start date");
        if (dto.payoutModel() == PayoutModel.CPA_PERCENT) {
            if (dto.payoutValue().compareTo(BigDecimal.ZERO) < 0 || dto.payoutValue().compareTo(BigDecimal.valueOf(100)) > 0)
                throw new ConflictException("CPA percentage must be between 0 and 100");
        } else if (dto.payoutValue().compareTo(BigDecimal.ZERO) <= 0)
            throw new ConflictException("Payout value must be positive");
        Campaign campaign = Campaign.builder()
                .name(dto.name())
                .startDate(dto.startDate())
                .endDate(dto.endDate()).budget(dto.budget())
                .payoutModel(dto.payoutModel())
                .payoutValue(dto.payoutValue()).build();
        offer.addCampaign(campaign);
        return toDto(campaignRepository.save(campaign));
    }

    @Transactional
    public void changeStatus(Long id, CampaignStatus status) {
        Campaign campaign = campaignRepository.findLockedById(id).orElseThrow(() -> new ResourceNotFoundException("Campaign not found"));
        if (status == CampaignStatus.ACTIVE) {
            List<Campaign> current = campaignRepository.findByOfferIdAndStatus(campaign.getOffer().getId(), CampaignStatus.ACTIVE);
            current.stream().filter(c -> !c.getId().equals(id)).forEach(c -> c.transitionTo(CampaignStatus.PAUSED));
            campaignRepository.flush();
        }
        campaign.transitionTo(status);
    }

    @Transactional(readOnly = true)
    public List<CampaignResDto> getActive() {
        return campaignRepository.findByStatus(CampaignStatus.ACTIVE).stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public List<CampaignResDto> getByStatus(CampaignStatus status) {
        return campaignRepository.findByStatus(status).stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public List<CampaignResDto> getByOffer(Long offerId) {
        return campaignRepository.findByOfferIdAndStatus(offerId, CampaignStatus.ACTIVE).stream().map(this::toDto).toList();
    }

    @Transactional
    public void pauseActiveForAdvertiser(Long advertiserId) {
        campaignRepository.findByOfferAdvertiserIdAndStatus(advertiserId, CampaignStatus.ACTIVE).forEach(Campaign::pauseForAdvertiserSuspension);
    }

    private CampaignResDto toDto(Campaign c) {
        return new CampaignResDto(c.getId(), c.getOffer().getId(), c.getName(), c.getStartDate(), c.getEndDate(), c.getBudget(), c.getPayoutModel(), c.getPayoutValue(), c.getStatus());
    }
}
