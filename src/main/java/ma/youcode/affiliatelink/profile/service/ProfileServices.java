package ma.youcode.affiliatelink.profile.service;

import lombok.AllArgsConstructor;
import ma.youcode.affiliatelink.profile.dto.request.ProfileReqDto;
import ma.youcode.affiliatelink.profile.dto.response.ProfileResDto;
import ma.youcode.affiliatelink.profile.entity.Profile;
import ma.youcode.affiliatelink.profile.enums.ProfileStatue;
import ma.youcode.affiliatelink.profile.repository.ProfileRepository;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class ProfileServices {
    private final ProfileRepository profileRepository;

    public ProfileResDto update(Long id, ProfileReqDto dto) {
        Profile profile = profileRepository.findById(id).orElseThrow(() -> new RuntimeException("id nout found"));


        ProfileStatue newStatus = dto.statue();

        profile.changeStatus(newStatus);
        profileRepository.save(profile);

        return ProfileResDto.builder()
                .id(profile.getId())
                .email(profile.getEmail())
                .statue(profile.getStatus())
                .createdAt(profile.getCreatedAt()).
                build();
    }

    public List<ProfileResDto> getAll() {
        return profileRepository.findAll()
                .stream()
                .map(profile -> ProfileResDto.builder()
                        .id(profile.getId())
                        .email(profile.getEmail())
                        .statue(profile.getStatus())
                        .createdAt(profile.getCreatedAt())
                        .build())
                .toList();
    }
}
