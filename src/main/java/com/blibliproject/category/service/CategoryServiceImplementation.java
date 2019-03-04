package com.blibliproject.category.service;

import com.blibliproject.category.model.Category;
import com.blibliproject.category.repository.CategoryRepository;

import java.util.ArrayList;

public class CategoryServiceImplementation implements CategoryService{

    private final CategoryRepository categoryRepository;

    public CategoryServiceImplementation(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ArrayList<Category> findAll() {
        return categoryRepository.readAll();
    }

    @Override
    public Category findCategoryById(Integer id) {
        return categoryRepository.readById(id);
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.create(category);
    }
}
