package ma.youcode.affiliatelink.profile.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.youcode.affiliatelink.profile.dto.request.ProfileReqDto;
import ma.youcode.affiliatelink.profile.service.ProfileServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/profiles")
@AllArgsConstructor
public class AdminProfileController {
    private final ProfileServices profileServices;

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateStatues(@PathVariable Long id , @Valid @RequestBody ProfileReqDto dto){
        profileServices.update(id , dto);
        return ResponseEntity.noContent().build();
    }
}
