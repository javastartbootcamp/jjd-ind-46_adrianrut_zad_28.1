package pl.javastart.restoffers.offer;

import org.springframework.stereotype.Service;
import pl.javastart.restoffers.category.Category;
import pl.javastart.restoffers.category.CategoryRepository;

@Service
public class OfferDtoMapper {

    private final CategoryRepository categoryRepository;

    public OfferDtoMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Offer map(OfferDto dto) {
        Offer offer = new Offer();
        offer.setTitle(dto.getTitle());
        offer.setDescription(dto.getDescription());
        Category category = categoryRepository.findByName(dto.getCategory());
        offer.setCategory(category);
        offer.setImgUrl(dto.getImgUrl());
        offer.setPrice(dto.getPrice());
        offer.setImgUrl(dto.getImgUrl());
        return offer;
    }

    public OfferDto mapToDto(Offer offer) {
        OfferDto dto = new OfferDto();
        dto.setId(offer.getId());
        dto.setTitle(offer.getTitle());
        dto.setDescription(offer.getDescription());
        dto.setImgUrl(offer.getImgUrl());
        dto.setPrice(offer.getPrice());
        dto.setCategory(offer.getCategory().getName());
        return dto;
    }
}
