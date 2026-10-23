package ma.youcode.affiliatelink.profile.service;

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

    public List<AdvertiserResDto> getAllAdvertisers(){
        List<Advertiser> advertisers = advertiserRepository.findAll();

        return advertisers.stream()
                .map(a-> AdvertiserResDto.builder()
                        .id(a.getId())
                        .email(a.getEmail())
                        .statue(a.getStatus())
                        .createdAt(a.getCreatedAt())
                        .companyName(a.getCompanyName())
                        .sector(a.getSector())
                        .website(a.getWebsite())
                        .build()
                ).toList();
    }

    public AdvertiserResDto getAdvertiserById(Long id){
        Advertiser advertiser = advertiserRepository.findById(id).orElseThrow(() -> new RuntimeException("id nout found"));
        return AdvertiserResDto.builder()
                .id(advertiser.getId())
                .email(advertiser.getEmail())
                .statue(advertiser.getStatus())
                .createdAt(advertiser.getCreatedAt())
                .companyName(advertiser.getCompanyName())
                .sector(advertiser.getSector())
                .website(advertiser.getWebsite())
                .build();
    }
}
