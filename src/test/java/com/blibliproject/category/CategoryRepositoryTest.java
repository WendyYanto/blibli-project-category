package com.blibliproject.category;

import com.blibliproject.category.model.Category;
import com.blibliproject.category.repository.CategoryRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Before
    public void setUp(){
        categoryRepository.deleteAll().block();
    }

    @Test
    public void categoryListMustReturnNull(){
        Assert.assertTrue("List data Must Return 0",categoryRepository.count().block() == 0);
    }

    @Test
    public void categoryCreatedMustReturnTheSameObjectInserted(){

        Category data = new Category(
            "1",
            "Makanan"
        );

        categoryRepository.save(data).block();

        Category result = categoryRepository.findById("1").block();

        Assert.assertNotNull("result must not null",result);
        Assert.assertTrue("data and result must return the same name",result.getName().equals(data.getName()));
    }

    @Test
    public void categoryNotCreatedCannotBeFindByIdMustReturnNull(){
        Category data = new Category(
            "1",
            "Makanan"
        );

        categoryRepository.save(data).block();
        Category result = categoryRepository.findById("200").block();
        Assert.assertNull("result must null",result);
    }

    @Test
    public void categoryGetAllMustHaveTheSameSizeAndObject() {
        Category first_data = new Category(
            "1",
            "Makanan"
        );

        Category second_data = new Category(
            "2",
            "Minuman"
        );

        List<Category> totals = new ArrayList<Category>();
        totals.add(first_data);
        totals.add(second_data);

        categoryRepository.saveAll(totals).collectList().block();

        Category firstReturnID = categoryRepository.findById("1").block();
        Category secondReturnID = categoryRepository.findById("2").block();

        Assert.assertTrue("secondReturnID Must Return Same Name",second_data.getName().equals(secondReturnID.getName()));
        Assert.assertTrue("firstReturnID Must Return Same Name",first_data.getName().equals(firstReturnID.getName()));
    }

    @Test
    public void categoryUpdateMustReturnUpdatedObjectAndSameID() {

        Category first_data = new Category(
            "1",
            "Makanan"
        );

        Category update_data = new Category(
            "1",
            "Minuman"
        );

        categoryRepository.save(first_data).block();
        categoryRepository.save(update_data).block();

        Category firstID = categoryRepository.findById("1").block();
        Assert.assertTrue("Category OF ID 1 have changed",firstID.getName().equals(update_data.getName()));
    }

    @Test
    public void deleteCategory() {
        Category first_data = new Category(
            "1",
            "Makanan"
        );

        Category second_data = new Category(
            "2",
            "Minuman"
        );

        categoryRepository.save(first_data).block();
        categoryRepository.save(second_data).block();

        categoryRepository.deleteById("1").block();

        List<Category> list_data = categoryRepository.findAll().collectList().block();

        Category returnID1 = categoryRepository.findById("1").block();

        Assert.assertTrue("list_data must return size of 1",list_data.size() == 1);
        Assert.assertNull("returnID1 must return NULL",returnID1);
    }
}
