package com.blibliproject.category.service;

import com.blibliproject.category.model.Category;
import com.blibliproject.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImplementation implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> findById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            return category;
        }

        return null;
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category update(Category category, Long id) {
        Category current = new Category();
        current.setName(category.getName());
        current.setId(id);
        return categoryRepository.save(current);
    }

    @Override
    public Category delete(Long id) {
        Optional<Category> current = categoryRepository.findById(id);
        if(current.isPresent()){
            Category deletedCategory = current.get();
            categoryRepository.delete(current.get());
            return deletedCategory;
        }

        return null;
    }
}
