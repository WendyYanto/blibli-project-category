package com.blibliproject.category.service;

import com.blibliproject.category.model.Category;
import com.blibliproject.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CategoryServiceImplementation implements CategoryService{

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImplementation(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Mono<Category> create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Mono<Category> findById(String id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Flux<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Mono<Category> update(Category category, String id) {
        return categoryRepository.findById(id)
            .map(value -> new Category(value.getId(),category.getName()))
            .flatMap(value -> categoryRepository.save(value)
                .thenReturn(value)
        );
    }

    @Override
    public Mono<Category> delete(String id) {
        return categoryRepository.findById(id)
            .flatMap(value -> categoryRepository.deleteById(id)
                .thenReturn(value)
        );
    }
}
