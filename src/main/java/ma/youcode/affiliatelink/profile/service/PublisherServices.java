package ma.youcode.affiliatelink.profile.service;

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

    public List<PublisherResDto> getAllPublisher(){
        List<Publisher> publisher = publisherRepository.findAll();

        List<PublisherResDto> publishers = publisher.stream()
                .map(p->PublisherResDto.builder()
                        .id(p.getId())
                        .email(p.getEmail())
                        .firstName(p.getFirstName())
                        .lastName(p.getLastName())
                        .niche(p.getNiche())
                        .bio(p.getBio())
                        .country(p.getCountry())
                        .statue(p.getStatus())
                        .createdAt(p.getCreatedAt())
                        .build()

                ).toList();

        return publishers;
    }

    public PublisherResDto getPublisherById(Long id){
        Publisher publisher = publisherRepository.findById(id).orElseThrow(() -> new RuntimeException("id nout found"));

        return PublisherResDto.builder()
                .id(publisher.getId())
                .email(publisher.getEmail())
                .firstName(publisher.getFirstName())
                .lastName(publisher.getLastName())
                .niche(publisher.getNiche())
                .bio(publisher.getBio())
                .country(publisher.getCountry())
                .statue(publisher.getStatus())
                .createdAt(publisher.getCreatedAt())
                .build();

    }
}
