package com.blibliproject.category.controller;

import com.blibliproject.category.model.Category;
import com.blibliproject.category.security.ApiKey;
import com.blibliproject.category.service.CategoryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(
        value = "/categories",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Flux<Category> getAllCategories(){
        return categoryService.getAll()
                .subscribeOn(Schedulers.elastic());
    }

    @RequestMapping(
        value = "/categories/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<Category> findById(@PathVariable("id") String id, ApiKey apiKey){
        return categoryService.findById(id)
            .subscribeOn(Schedulers.elastic());
    }

    @RequestMapping(
        value = "/categories",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<Category> create(@RequestBody Category category, ApiKey apiKey){
        return categoryService.create(category)
            .subscribeOn(Schedulers.elastic());
    }

    @RequestMapping(
        value = "/categories/edit/{id}",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<Category> update(@RequestBody Category category,@PathVariable("id") String id, ApiKey apiKey){
        return categoryService.update(category,id)
                .subscribeOn(Schedulers.elastic());
    }

    @RequestMapping(
        value = "/categories/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<Category> delete(@PathVariable("id") String id, ApiKey apiKey){
        return categoryService.delete(id)
                .subscribeOn(Schedulers.elastic());
    }
}
