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
            .flatMap(value -> {
                category.setId(value.getId());
                return categoryRepository.save(category);
            });
    }

    @Override
    public Mono<Category> delete(String id) {
        return categoryRepository.findById(id)
            .map(value -> {
                categoryRepository.deleteById(id);
                return value;
            });
    }

    //Penjelasan FlatMap dan Map;
//    public Mono<Category> edit(String id, String name){
//        return categoryRepository.findById(id)
//            .map(category -> getCategory(name, category))
//            .flatMap(category -> categoryRepository.save(category));
//    }
//
//    private Category getCategory(String name, Category category) {
//        category.setName(name);
//        return category;
//    }
}
