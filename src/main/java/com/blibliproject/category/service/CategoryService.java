package com.blibliproject.category.service;

import com.blibliproject.category.model.Category;

import java.util.ArrayList;
import java.util.List;

public interface CategoryService {

    public ArrayList<Category> findAll();

    public Category findCategoryById(Integer id);

    Category saveCategory(Category category);
}
