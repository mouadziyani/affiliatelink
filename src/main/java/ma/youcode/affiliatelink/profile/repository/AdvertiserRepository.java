package ma.youcode.affiliatelink.profile.repository;

import ma.youcode.affiliatelink.profile.entity.Advertiser;
import ma.youcode.affiliatelink.profile.enums.ProfileStatue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdvertiserRepository extends JpaRepository<Advertiser,Long> {
    boolean existsByEmail(String email);
    boolean existsByIdAndStatus(Long id, ProfileStatue status);
}
