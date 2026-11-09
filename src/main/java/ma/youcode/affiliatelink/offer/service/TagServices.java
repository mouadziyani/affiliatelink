package ma.youcode.affiliatelink.offer.service;

import lombok.RequiredArgsConstructor;
import ma.youcode.affiliatelink.offer.dto.request.TagReqDto;
import ma.youcode.affiliatelink.offer.dto.response.TagResDto;
import ma.youcode.affiliatelink.offer.entity.Tag;
import ma.youcode.affiliatelink.offer.repository.TagRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServices {
    private final TagRepository repository;

    @Transactional
    public TagResDto create(TagReqDto dto) {
        String name = dto.name().trim().toLowerCase();
        Tag tag = repository.findByName(name).orElseGet(() -> repository.save(new Tag(name)));
        return toDto(tag);
    }

    @Transactional(readOnly = true)
    public List<TagResDto> getAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    private TagResDto toDto(Tag tag) {
        return new TagResDto(tag.getId(), tag.getName());
    }
}
