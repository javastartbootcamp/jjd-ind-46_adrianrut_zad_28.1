package pl.javastart.restoffers.category;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryDtoMapper categoryDtoMapper;

    public CategoryService(CategoryRepository categoryRepository, CategoryDtoMapper categoryDtoMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryDtoMapper = categoryDtoMapper;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public List<String> findNames() {
        List<String> categoryList = new ArrayList<>();
        for (Category category : categoryRepository.findAll()) {
            String categoryName = category.getName();
            categoryList.add(categoryName);
        }
        return categoryList;
    }

    CategoryDto saveCompany(CategoryDto categoryDto) {
        Category category = categoryDtoMapper.map(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return categoryDtoMapper.map(savedCategory);
    }

    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }
}
