package pl.javastart.restoffers.offer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("/api/offers")
@RestController
public class OfferRestController {

    private final OfferService offerService;

    public OfferRestController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping("")
    public List<Offer> findAll(@RequestParam(required = false) String title) {
        if (title != null) {
            return offerService.findByTitle(title);
        }
        return offerService.findAll();
    }

    @GetMapping("/count")
    public int countOffers() {
        return offerService.countOffers();
    }

    @PostMapping("")
    ResponseEntity<OfferDto> saveOffer(@RequestBody OfferDto offerDto) {
        OfferDto savedOffer = offerService.saveOffer(offerDto);
        URI savedOfferUri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedOffer.getId())
                .toUri();
        return ResponseEntity.created(savedOfferUri).body(savedOffer);
    }

    @GetMapping("/{id}")
    ResponseEntity<OfferDto> getOfferById(@PathVariable Long id) {
        return offerService.getOfferById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteOffer(@PathVariable Long id) {
        offerService.deleteOfferById(id);
        return ResponseEntity.noContent().build();
    }

}
