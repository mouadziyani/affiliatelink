package ma.youcode.affiliatelink.offer.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.youcode.affiliatelink.offer.dto.request.TagReqDto;
import ma.youcode.affiliatelink.offer.dto.response.TagResDto;
import ma.youcode.affiliatelink.offer.service.TagServices;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController @RequestMapping("/api/tags") @RequiredArgsConstructor
public class TagController {
    private final TagServices services;
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public TagResDto create(@Valid @RequestBody TagReqDto dto) { return services.create(dto); }
    @GetMapping public List<TagResDto> getAll() { return services.getAll(); }
}
