
import lombok.*;
import ma.youcode.affiliatelink.profile.dto.request.AdvertiserReqDto;
import ma.youcode.affiliatelink.profile.dto.response.AdvertiserResDto;
import ma.youcode.affiliatelink.profile.entity.Advertiser;
import ma.youcode.affiliatelink.profile.repository.AdvertiserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdvertiserServices {
    public final AdvertiserRepository advertiserRepository ;

    public AdvertiserResDto createAdvertiser(AdvertiserReqDto dto){
        if (advertiserRepository.existsByEmail(dto.email())){
                throw new RuntimeException("email exist");
        }

        Advertiser advertiser = Advertiser.builder()
                .email(dto.email())
                .companyName(dto.companyName())
                .sector(dto.sector())
                .website(dto.website().name())
                .build();

        Advertiser create = advertiserRepository.save(advertiser);

        return AdvertiserResDto.builder()
                .id(create.getId())
                .email(create.getEmail())
                .statue(create.getStatus())
                .createdAt(create.getCreatedAt())
                .companyName(create.getCompanyName())
                .sector(create.getSector())
                .website(create.getWebsite())
                .build();
    }
}
