package ma.youcode.affiliatelink.offer.repository;

import ma.youcode.affiliatelink.offer.entity.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ma.youcode.affiliatelink.offer.enums.OfferNiche;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    boolean existsByAdvertiserIdAndTitle(Long advertiserId, String title);
    boolean existsByAdvertiserIdAndTitleAndIdNot(Long advertiserId, String title, Long id);

    @Query("select distinct o from Offer o left join o.tags t where " +
            "(:niche is null or o.offerNiche = :niche) and " +
            "(:advertiserId is null or o.advertiserId = :advertiserId) and " +
            "(:tag is null or t.name = :tag) and " +
            "(:keyword is null or lower(o.title) like lower(concat('%', :keyword, '%')))" )
    Page<Offer> search(@Param("niche") OfferNiche niche, @Param("advertiserId") Long advertiserId,
                       @Param("tag") String tag, @Param("keyword") String keyword, Pageable pageable);
}
