package com.blibliproject.category.service;

import com.blibliproject.category.model.Category;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public interface CategoryService {

    Mono<Category> create(Category category);

    Mono<Category> findById(String id);

    Flux<Category> getAll();

    Mono<Category> update(Category category, String id);

    Mono<Category> delete(String id);
}
