package ma.youcode.affiliatelink.profile.service;

import jakarta.transaction.Transactional;
import ma.youcode.affiliatelink.profile.ProfileApi;
import ma.youcode.affiliatelink.profile.entity.Advertiser;
import ma.youcode.affiliatelink.profile.enums.ProfileStatue;
import ma.youcode.affiliatelink.profile.repository.AdvertiserRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfileApiImpl implements ProfileApi {
    private final AdvertiserRepository advertiserRepository;

    public ProfileApiImpl(AdvertiserRepository advertiserRepository) {
        this.advertiserRepository = advertiserRepository;
    }

    @Override
    public boolean isActiveAdvertiser(Long advertiserId) {
        return advertiserRepository.findById(advertiserId)
                .map(Advertiser::getStatus)
                .filter(status -> status == ProfileStatue.ACTIVE)
                .isPresent();

    }
}