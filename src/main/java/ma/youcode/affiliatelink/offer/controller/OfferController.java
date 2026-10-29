package ma.youcode.affiliatelink.offer.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.youcode.affiliatelink.offer.dto.request.OfferReqDto;
import ma.youcode.affiliatelink.offer.dto.response.OfferResDto;
import ma.youcode.affiliatelink.offer.service.OfferServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/offres")
@AllArgsConstructor
public class OfferController {
    private final OfferServices offerServices;

    @PostMapping
    public ResponseEntity<OfferResDto> createOffer(@Valid @RequestBody OfferReqDto dto) {
        OfferResDto offer = offerServices.createOffer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(offer);
    }

}
