package pl.javastart.restoffers.offer;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final OfferDtoMapper offerDtoMapper;

    public OfferService(OfferRepository offerRepository, OfferDtoMapper offerDtoMapper) {
        this.offerRepository = offerRepository;
        this.offerDtoMapper = offerDtoMapper;
    }

    public List<Offer> findAll() {
        return offerRepository.findAll();
    }

    public List<Offer> findByTitle(String title) {
        return offerRepository.findAllByTitleContainingIgnoreCase(title);
    }

    public int countOffers() {
        List<Offer> offerList = offerRepository.findAll();
        return offerList.size();
    }

    OfferDto saveOffer(OfferDto offerDto) {
        Offer offer = offerDtoMapper.map(offerDto);
        Offer savedOffer = offerRepository.save(offer);
        return offerDtoMapper.mapToDto(savedOffer);
    }

    Optional<OfferDto> getOfferById(Long id) {
        return offerRepository.findById(id)
                .map(offerDtoMapper::mapToDto);
    }

    public void deleteOfferById(Long id) {
        offerRepository.deleteById(id);
    }

}
