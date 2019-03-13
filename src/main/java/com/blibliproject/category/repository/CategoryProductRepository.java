package com.blibliproject.category.repository;

import com.blibliproject.category.model.CategoryHasProduct;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CategoryProductRepository extends ReactiveMongoRepository<CategoryHasProduct,String> {
}
