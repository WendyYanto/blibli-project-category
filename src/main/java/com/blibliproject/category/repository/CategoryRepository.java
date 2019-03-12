package com.blibliproject.category.repository;

import com.blibliproject.category.model.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CategoryRepository extends ReactiveMongoRepository<Category,String> {

}
