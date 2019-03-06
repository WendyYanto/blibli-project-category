package com.blibliproject.category.service;

import com.blibliproject.category.model.Category;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface CategoryService {

    Category create(Category category);

    Category findById(int id);

    List<Category> getAll();

    Category update(Category category, int id);

    Category delete(int id);
}
