package ma.youcode.affiliatelink.profile.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.youcode.affiliatelink.profile.dto.request.PublisherReqDto;
import ma.youcode.affiliatelink.profile.dto.response.PublisherResDto;
import ma.youcode.affiliatelink.profile.service.PublisherServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
@AllArgsConstructor
public class PublisherController {
    private final PublisherServices publisherServices;

    @PostMapping
    public ResponseEntity<PublisherResDto> createPublisher(@Valid @RequestBody PublisherReqDto dto){
        PublisherResDto publisher = publisherServices.createPub(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(publisher);
    }

    @GetMapping
    public ResponseEntity<?> getAllPublisher(){
        List<PublisherResDto> publishers = publisherServices.getAllPublisher();
        return  ResponseEntity.ok(publishers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PublisherResDto> getPublisherById(@PathVariable("id") Long id){
        PublisherResDto publisher = publisherServices.getPublisherById(id);

        return ResponseEntity.ok(publisher);
    }
}
