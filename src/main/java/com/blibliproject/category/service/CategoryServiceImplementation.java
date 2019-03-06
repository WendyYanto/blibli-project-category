package com.blibliproject.category.service;

import com.blibliproject.category.model.Category;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImplementation implements CategoryService{

    private List<Category> data = new ArrayList<Category>();

    @Override
    public Category create(Category category) {

        if(data.size() > 0 && findById(category.getId()) != null){
            return null;
        }

        data.add(category);
        return category;

    }

    @Override
    public Category findById(int id) {

        for(int i=0;i<data.size();i++){
            if(data.get(i).getId() == id){
                return data.get(i);
            }
        }

        return null;
    }

    @Override
    public List<Category> getAll() {
        if(data.size() == 0){
            return null;
        }

        return data;
    }

    @Override
    public Category update(Category category, int id) {
        Category current = findById(id);

        if(current != null){
            current.setName(category.getName());
            return current;
        }

        return null;
    }

    @Override
    public Category delete(int id) {
        Category current = findById(id);

        if(current != null){
            data.remove(current);
            return current;
        }

        return null;
    }
}
