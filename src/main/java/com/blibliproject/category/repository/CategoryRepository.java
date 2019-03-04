package com.blibliproject.category.repository;

import com.blibliproject.category.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository implements Repository<Category,Integer>{

    ArrayList<Category> categories = new ArrayList<Category>();

    @Override
    public ArrayList<Category> readAll() {
        return categories;
    }

    @Override
    public Category readById(Integer index) {
        return categories.get(index);
    }

    @Override
    public Category create(Category data) {
        categories.add(data);
        return data;
    }
}
