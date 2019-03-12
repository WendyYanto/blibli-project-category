package com.blibliproject.category;

import com.blibliproject.category.repository.CategoryRepository;
import com.blibliproject.category.service.CategoryServiceImplementation;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryServiceTest {

    private CategoryServiceImplementation categoryService;

    @Autowired
    public CategoryServiceTest(CategoryServiceImplementation categoryService) {
        Mockito.mock(CategoryRepository.class);
        this.categoryService = categoryService;

    }
}
