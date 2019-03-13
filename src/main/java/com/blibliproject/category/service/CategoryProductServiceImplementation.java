package com.blibliproject.category.service;

import com.blibliproject.category.model.CategoryHasProduct;
import com.blibliproject.category.repository.CategoryProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CategoryProductServiceImplementation implements CategoryProductService{

    private CategoryProductRepository categoryProductRepository;

    @Autowired
    public CategoryProductServiceImplementation(CategoryProductRepository categoryProductRepository) {
        this.categoryProductRepository = categoryProductRepository;
    }

    @Override
    public Mono<CategoryHasProduct> create(CategoryHasProduct categoryHasProduct) {
        return categoryProductRepository.save(categoryHasProduct);
    }
}
