package ma.youcode.affiliatelink.offer.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ma.youcode.affiliatelink.offer.dto.request.CampaignReqDto;
import ma.youcode.affiliatelink.offer.dto.response.CampaignResDto;
import ma.youcode.affiliatelink.offer.enums.CampaignStatus;
import ma.youcode.affiliatelink.offer.service.CampaignServices;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CampaignController {
    private final CampaignServices services;

    @PostMapping("/offres/{offerId}/campaigns")
    @ResponseStatus(HttpStatus.CREATED)
    public CampaignResDto create(@PathVariable Long offerId, @Valid @RequestBody CampaignReqDto dto) {
        return services.create(offerId, dto);
    }

    @GetMapping("/campaigns")
    public List<CampaignResDto> get(@RequestParam(defaultValue = "ACTIVE") CampaignStatus status) {
        return services.getByStatus(status);
    }

    @PatchMapping("/campaigns/{id}/status")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeStatus(@PathVariable Long id, @RequestParam CampaignStatus status) {
        services.changeStatus(id, status);
    }
}
