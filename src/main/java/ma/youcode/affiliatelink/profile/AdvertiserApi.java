package ma.youcode.affiliatelink.profile;

import lombok.RequiredArgsConstructor;
import ma.youcode.affiliatelink.profile.repository.AdvertiserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdvertiserApi {
    private final AdvertiserRepository advertiserRepository;

    @Transactional(readOnly = true)
    public boolean existsById(Long advertiserId) {
        return advertiserRepository.existsById(advertiserId);
    }
}
