package ma.youcode.affiliatelink.offer.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.youcode.affiliatelink.offer.dto.request.OfferReqDto;
import ma.youcode.affiliatelink.offer.dto.response.OfferResDto;
import ma.youcode.affiliatelink.offer.service.OfferServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.server.ResponseStatusException;
import ma.youcode.affiliatelink.offer.enums.OfferNiche;

@RestController
@RequestMapping("/api/offres")
@AllArgsConstructor
public class OfferController {
    private final OfferServices offerServices;

    @GetMapping
    public Page<OfferResDto> search(
            @RequestParam(required = false) OfferNiche niche,
            @RequestParam(required = false) Long advertiserId,
            @RequestParam(required = false) String tag,
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "createdAt,desc") String sort) {
        if (page < 0 || size < 1 || size > 100) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid page or size");
        String[] sortParts = sort.split(",");
        String property = sortParts[0];
        if (!java.util.Set.of("createdAt", "title", "advertiserId", "offerNiche").contains(property))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid sort property");
        if (sortParts.length > 2 || (sortParts.length == 2
                && !sortParts[1].equalsIgnoreCase("asc") && !sortParts[1].equalsIgnoreCase("desc")))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid sort direction");
        Sort.Direction direction = sortParts.length > 1 && sortParts[1].equalsIgnoreCase("desc")
                ? Sort.Direction.DESC : Sort.Direction.ASC;
        Sort sorting = Sort.by(direction, property);
        Pageable pageable = PageRequest.of(page, size, sorting);
        return offerServices.search(niche, advertiserId, tag, keyword, pageable);
    }

    @PostMapping
    public ResponseEntity<OfferResDto> createOffer(@Valid @RequestBody OfferReqDto dto) {
        OfferResDto offer = offerServices.createOffer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(offer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferResDto> getOfferById(@PathVariable Long id) {
        OfferResDto offer = offerServices.getOfferById(id);
        return ResponseEntity.ok(offer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OfferResDto> updateOffer(
            @PathVariable Long id,
            @Valid @RequestBody OfferReqDto dto
    ) {
        OfferResDto offer = offerServices.updateOffer(id, dto);
        return ResponseEntity.ok(offer);
    }
}
