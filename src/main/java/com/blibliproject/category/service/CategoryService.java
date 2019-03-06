package com.blibliproject.category.service;

import com.blibliproject.category.model.Category;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public interface CategoryService {

    Category create(Category category);

    Optional<Category> findById(Long id);

    List<Category> getAll();

    Category update(Category category, Long id);

    Category delete(Long id);
}
