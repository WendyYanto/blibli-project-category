package com.blibliproject.category.controller;

import com.blibliproject.category.model.Category;
import com.blibliproject.category.service.CategoryService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController {

    private CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @RequestMapping(
        value = "/categories",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public List<Category> getAllCategories(){
        return service.getAll();
    }

    @RequestMapping(
        value = "/categories/{id}",
        method = RequestMethod.GET,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Category findById(@PathVariable("id") int id){
        return service.findById(id);
    }

    @RequestMapping(
        value = "/categories",
        method = RequestMethod.POST,
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Category create(@RequestBody Category category){
        return service.create(category);
    }

    @RequestMapping(
        value = "/categories/edit/{id}",
        method = RequestMethod.PUT,
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public Category update(@RequestBody Category category,@PathVariable("id") int id){
        return service.update(category,id);
    }

    @RequestMapping(
        value = "/categories/{id}",
        method = RequestMethod.DELETE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Category delete(@PathVariable("id") int id){
        return service.delete(id);
    }
}
