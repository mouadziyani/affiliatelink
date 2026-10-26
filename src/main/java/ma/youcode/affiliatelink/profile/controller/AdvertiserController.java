package ma.youcode.affiliatelink.profile.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.youcode.affiliatelink.profile.dto.request.AdvertiserReqDto;
import ma.youcode.affiliatelink.profile.dto.response.AdvertiserResDto;
import ma.youcode.affiliatelink.profile.entity.Advertiser;
import ma.youcode.affiliatelink.profile.service.AdvertiserServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/advertisers")
@AllArgsConstructor
public class AdvertiserController {
    private final AdvertiserServices advertiserServices;

    @PostMapping
    public ResponseEntity<AdvertiserResDto> createAdvertiser(@Valid @RequestBody AdvertiserReqDto dto){
        AdvertiserResDto advertiser = advertiserServices.createAdvertiser(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(advertiser);
    }

    @GetMapping
    public ResponseEntity<?> getAllAdvertiser(){
        List<AdvertiserResDto> advertiserResDtos = advertiserServices.getAllAdvertisers();
        return ResponseEntity.ok(advertiserResDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdvertiserResDto> getAllById(@PathVariable Long id){
        AdvertiserResDto advertiserResDto = advertiserServices.getAdvertiserById(id);
        return ResponseEntity.ok(advertiserResDto);
    }

}
