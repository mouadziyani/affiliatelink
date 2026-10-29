package ma.youcode.affiliatelink.offer.service;

import lombok.AllArgsConstructor;
import ma.youcode.affiliatelink.offer.dto.request.OfferReqDto;
import ma.youcode.affiliatelink.offer.dto.response.OfferResDto;
import ma.youcode.affiliatelink.offer.entity.Offer;
import ma.youcode.affiliatelink.offer.repository.OfferRepository;
import ma.youcode.affiliatelink.profile.AdvertiserApi;
import ma.youcode.affiliatelink.shared.exception.ConflictException;
import ma.youcode.affiliatelink.shared.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OfferServices {

    private final OfferRepository offerRepository;
    private final AdvertiserApi advertiserApi;

    public OfferResDto createOffer(OfferReqDto dto) {

        if (!advertiserApi.existsById(dto.advertiserId())) {
            throw new ResourceNotFoundException(
                    "Advertiser not found"
            );
        }

        if (offerRepository.existsByAdvertiserIdAndTitle(
                dto.advertiserId(), dto.title())) {
            throw new ConflictException(
                    "Advertiser already has an offer with this title"
            );
        }

        Offer offer = Offer.builder()
                .advertiserId(dto.advertiserId())
                .title(dto.title())
                .description(dto.description())
                .landingUrl(dto.landingUrl())
                .offerNiche(dto.offerNiche())
                .build();

        Offer created = offerRepository.save(offer);

        return toResDto(created);
    }

    public OfferResDto getOfferById(Long id) {

        Offer offer = offerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Offer with id " + id + " not found"
                ));

        return toResDto(offer);
    }

}