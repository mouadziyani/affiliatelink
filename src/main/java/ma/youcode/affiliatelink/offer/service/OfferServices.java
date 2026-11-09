package ma.youcode.affiliatelink.offer.service;

import lombok.RequiredArgsConstructor;
import ma.youcode.affiliatelink.offer.dto.request.OfferReqDto;
import ma.youcode.affiliatelink.offer.dto.response.CampaignResDto;
import ma.youcode.affiliatelink.offer.dto.response.OfferResDto;
import ma.youcode.affiliatelink.offer.entity.Offer;
import ma.youcode.affiliatelink.offer.entity.Tag;
import ma.youcode.affiliatelink.offer.repository.OfferRepository;
import ma.youcode.affiliatelink.offer.repository.TagRepository;
import ma.youcode.affiliatelink.offer.enums.OfferNiche;
import ma.youcode.affiliatelink.profile.AdvertiserApi;
import ma.youcode.affiliatelink.shared.exception.ConflictException;
import ma.youcode.affiliatelink.shared.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferServices {
    private final OfferRepository offerRepository;
    private final TagRepository tagRepository;
    private final AdvertiserApi advertiserApi;

    @Transactional
    public OfferResDto createOffer(OfferReqDto dto) {
        requireActiveAdvertiser(dto.advertiserId());
        if (offerRepository.existsByAdvertiserIdAndTitle(dto.advertiserId(), dto.title()))
            throw new ConflictException("Advertiser already has an offer with this title");
        Offer offer = Offer.builder().advertiserId(dto.advertiserId()).title(dto.title())
                .description(dto.description()).landingUrl(dto.landingUrl()).offerNiche(dto.offerNiche()).build();
        setTags(offer, dto.tags());
        return toResDto(offerRepository.save(offer));
    }

    @Transactional(readOnly = true)
    public Page<OfferResDto> search(OfferNiche niche, Long advertiserId, String tag, String keyword, Pageable pageable) {
        String normalizedTag = tag == null || tag.isBlank() ? null : tag.trim().toLowerCase();
        String normalizedKeyword = keyword == null || keyword.isBlank() ? null : keyword.trim();
        return offerRepository.search(niche, advertiserId, normalizedTag, normalizedKeyword, pageable).map(this::toResDto);
    }

    @Transactional(readOnly = true)
    public OfferResDto getOfferById(Long id) {
        return toResDto(offerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Offer with id " + id + " not found")));
    }

    @Transactional
    public OfferResDto updateOffer(Long id, OfferReqDto dto) {
        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Offer with id " + id + " not found"));
        requireActiveAdvertiser(dto.advertiserId());
        if (offerRepository.existsByAdvertiserIdAndTitleAndIdNot(dto.advertiserId(), dto.title(), id))
            throw new ConflictException("Advertiser already has an offer with this title");
        offer.setAdvertiserId(dto.advertiserId());
        offer.setTitle(dto.title());
        offer.setDescription(dto.description());
        offer.setLandingUrl(dto.landingUrl());
        offer.setOfferNiche(dto.offerNiche());
        setTags(offer, dto.tags());
        return toResDto(offerRepository.save(offer));
    }

    private void requireActiveAdvertiser(Long advertiserId) {
        if (!advertiserApi.existsById(advertiserId)) throw new ResourceNotFoundException("Advertiser not found");
        if (!advertiserApi.isActive(advertiserId)) throw new ConflictException("Advertiser account must be active");
    }

    private void setTags(Offer offer, List<String> rawTags) {
        offer.getTags().clear();
        if (rawTags == null) return;
        for (String rawTag : new LinkedHashSet<>(rawTags)) {
            String name = rawTag.trim().toLowerCase();
            Tag tag = tagRepository.findByName(name).orElseGet(() -> tagRepository.save(new Tag(name)));
            offer.getTags().add(tag);
        }
    }

    private OfferResDto toResDto(Offer offer) {
        return new OfferResDto(offer.getId(), offer.getAdvertiserId(), offer.getTitle(), offer.getDescription(),
                offer.getLandingUrl(), offer.getOfferNiche(), offer.getCreatedAt(),
                offer.getTags().stream().map(Tag::getName).sorted().toList(),
                offer.getCampaigns().stream().map(c -> new CampaignResDto(c.getId(), offer.getId(), c.getName(),
                        c.getStartDate(), c.getEndDate(), c.getBudget(), c.getPayoutModel(), c.getPayoutValue(), c.getStatus())).toList());
    }
}
