package com.blibliproject.category.service;

import com.blibliproject.category.model.CategoryHasProduct;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public interface CategoryProductService {
    Mono<CategoryHasProduct> create(CategoryHasProduct categoryHasProduct);
}
