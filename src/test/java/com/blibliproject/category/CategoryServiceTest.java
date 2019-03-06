package com.blibliproject.category;

import com.blibliproject.category.model.Category;
import com.blibliproject.category.service.CategoryServiceImplementation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class CategoryServiceTest {

    private CategoryServiceImplementation service;

    @Before
    public void createCategoryServiceObject(){
        service = new CategoryServiceImplementation();
    }

    @Test
    public void categoryListMustReturnNull(){
        List<Category> data = service.getAll();
        Assert.assertTrue("List Should Return NULL",data == null);
    }

    @Test
    public void categoryCreatedMustReturnTheSameObjectInserted(){
        Category current = new Category(
            1,
            "Tiket"
        );

        Category response = service.create(current);

        List<Category> responseLists = service.getAll();

        Assert.assertTrue("Response Must Return The Same Object As Current",response == response);
        Assert.assertTrue("responseLists Must Have The Size of 1",responseLists.size() == 1);
    }

    @Test
    public void categoryCreatedCanBeFindById(){
        Category current = new Category(
                1,
                "Tiket"
        );

        service.create(current);

        Category categoryFindByIdEqualsOne = service.findById(1);

        Assert.assertTrue("Category Return On FindByID Function Must Have The Same Object As Current Object",categoryFindByIdEqualsOne == current);
    }

    @Test
    public void categoryNotCreatedCannotBeFindByIdMustReturnNull(){
        Category current = new Category(
            1,
            "Tiket"
        );

        service.create(current);

        Category categoryFindByIdEqualsTwo = service.findById(2);
        Assert.assertTrue("Category Return On FindByID Function Must Return NULL",categoryFindByIdEqualsTwo == null);
    }

    @Test
    public void categoryGetAllMustHaveTheSameSizeAndObject(){
        Category first_current = new Category(
            1,
            "Tiket"
        );

        Category second_current = new Category(
            2,
            "Mobil"
        );

        service.create(first_current);
        service.create(second_current);

        List<Category> responseLists = service.getAll();

        Assert.assertTrue("reponseLists Must Size Of 2",responseLists.size() == 2);
        Assert.assertTrue("findById(2) Must Return second_current Object",second_current == service.findById(2));
        Assert.assertTrue("findById(1) Must Return first_current Object",first_current == service.findById(1));
    }

    @Test
    public void categoryCannotInsertDuplicateID(){
        Category first_current = new Category(
            1,
            "Tiket"
        );

        Category second_current = new Category(
            1,
            "Tiket"
        );

        service.create(first_current);
        service.create(second_current);

        List<Category> responseLists = service.getAll();

        Assert.assertTrue("reponseLists Must Size Of 1",responseLists.size() == 1);
        Assert.assertTrue("findById(2) Must Return NULL",service.findById(2) == null);
        Assert.assertTrue("findById(1) Must Return first_current Object",first_current == service.findById(1));
    }

    @Test
    public void categoryUpdateMustReturnUpdatedObjectAndSameID(){
        Category first_current = new Category(
            1,
            "Tiket"
        );

        Category update_current = new Category(
            1,
            "Baju"
        );

        service.create(first_current);

        Category returnOnUpdateObject = service.update(update_current,1);

        Assert.assertTrue("returnOnUpdateObject Has ID of 1",returnOnUpdateObject.getId() == 1);
        Assert.assertTrue("returnOnUpdateObject Has Name of Baju",returnOnUpdateObject.getName().equals("Baju"));
    }

    @Test
    public void updateCategoryMustReturnNULLIfIDNotExists(){
        Category first_current = new Category(
            1,
            "Tiket"
        );

        service.create(first_current);

        Category returnOnUpdateObject = service.update(first_current,2);

        Assert.assertTrue("returnOnUpdateObject Must Return NULL",returnOnUpdateObject == null);
    }

    @Test
    public void deleteCategoryMustReturnNULLIfIDNotExists(){
        Category first_current = new Category(
            1,
            "Tiket"
        );

        service.create(first_current);

        Category response = service.delete(2);
        Assert.assertTrue("response Must Return NULL",response == null);
    }

    @Test
    public void deleteCategory(){
        Category first_current = new Category(
                1,
                "Tiket"
        );

        service.create(first_current);

        Category response = service.delete(1);

        List<Category> responseLists = service.getAll();

        Assert.assertTrue("response Must Return ID of 1",response.getId() == 1);
        Assert.assertTrue("responseLists Must Return NULL",responseLists == null);
    }
}
