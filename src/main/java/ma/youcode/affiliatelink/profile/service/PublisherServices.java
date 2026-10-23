
import lombok.AllArgsConstructor;
import ma.youcode.affiliatelink.profile.dto.request.PublisherReqDto;
import ma.youcode.affiliatelink.profile.dto.response.PublisherResDto;
import ma.youcode.affiliatelink.profile.entity.Publisher;
import ma.youcode.affiliatelink.profile.repository.PublisherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PublisherServices {

    public PublisherRepository publisherRepository;

    public PublisherResDto createPub(PublisherReqDto dto){

        if (publisherRepository.existsByEmail(dto.email())){
            throw new RuntimeException("email already exist");
        }

        Publisher publisher = Publisher.builder()
                .firstName(dto.firstName())
                .lastName(dto.lastName())
                .niche(dto.niche())
                .bio(dto.bio())
                .country(dto.country())
                .build();

        Publisher create = publisherRepository.save(publisher);

        return PublisherResDto.builder()
                .id(create.getId())
                .email(create.getEmail())
                .firstName(create.getFirstName())
                .lastName(create.getLastName())
                .niche(create.getNiche())
                .bio(create.getBio())
                .country(create.getCountry())
                .statue(create.getStatus())
                .createdAt(create.getCreatedAt())
                .build();

    }

}
