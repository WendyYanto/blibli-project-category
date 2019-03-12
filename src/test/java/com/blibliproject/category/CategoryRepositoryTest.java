//package com.blibliproject.category;
//
//import com.blibliproject.category.model.Category;
//import com.blibliproject.category.repository.CategoryRepository;
//import com.blibliproject.category.service.CategoryServiceImplementation;
//import org.junit.After;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//import java.util.Optional;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class CategoryRepositoryTest {
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//
//    @Test
//    public void categoryListMustReturnNull(){
//        Assert.assertTrue("List data Must Return 0",categoryRepository.count() == 0);
//    }
//
//    @Test
//    public void categoryCreatedMustReturnTheSameObjectInserted(){
//        Category firstCategory = new Category();
//        firstCategory.setName("Drinks");
//
//        Category returnCategoryCreated = categoryRepository.save(firstCategory);
//        Assert.assertTrue("firstCategory Is The Same Object As returnCategoryCreated",firstCategory == returnCategoryCreated);
//    }
//
//    @Test
//    public void categoryCreatedCanBeFindById(){
//        Category firstCategory = new Category();
//        firstCategory.setName("Drinks");
//
//        Category returnCategoryCreated = categoryRepository.save(firstCategory);
//
//        Long previousInsertedID = returnCategoryCreated.getId();
//        Optional<Category> findCategoryIDInserted = categoryRepository.findById(previousInsertedID);
//
//        Assert.assertTrue("ID Is The Same",findCategoryIDInserted.get().getId() == previousInsertedID);
//    }
//
//    @Test
//    public void categoryNotCreatedCannotBeFindByIdMustReturnNull(){
//        Category firstCategory = new Category();
//        firstCategory.setName("Drinks");
//
//        Category returnCategoryCreated = categoryRepository.save(firstCategory);
//
//        Long possibleNullObjectOfID = 500L;
//
//        Optional<Category> categoryIsNull = categoryRepository.findById(possibleNullObjectOfID);
//
//        Assert.assertTrue("CategoryIsNull Return False",categoryIsNull.isPresent() == false );
//    }
//
//    @Test
//    public void categoryGetAllMustHaveTheSameSizeAndObject() {
//        Category firstCategory = new Category();
//        firstCategory.setName("Drinks");
//
//        Category secondCategory = new Category();
//        secondCategory.setName("Foods");
//
//        Category firstCategoryID = categoryRepository.save(firstCategory);
//        Category secondCategoryID = categoryRepository.save(secondCategory);
//
//        Assert.assertTrue("List Size Return 2",categoryRepository.count() == 2);
//        Assert.assertTrue("Should Return True",categoryRepository.findById(firstCategoryID.getId()).get().getId() == firstCategoryID.getId());
//        Assert.assertTrue("Should Return True",categoryRepository.findById(secondCategoryID.getId()).get().getId() == secondCategoryID.getId());
//    }
//
//    @Test
//    public void categoryUpdateMustReturnUpdatedObjectAndSameID() {
//        Category firstCategory = new Category();
//        firstCategory.setName("Drinks");
//
//        Category getID = categoryRepository.save(firstCategory);
//
//        Category secondCategory = new Category();
//        secondCategory.setName("Foods");
//        secondCategory.setId(getID.getId());
//
//        Category returnCategory = categoryRepository.save(secondCategory);
//
//        Assert.assertTrue("ID Should Be The Same",getID.getId() == returnCategory.getId());
//    }
//
//    @Test
//    public void deleteCategory() {
//        Category firstCategory = new Category();
//        firstCategory.setName("Drinks");
//
//        Category getID = categoryRepository.save(firstCategory);
//        categoryRepository.deleteById(getID.getId());
//
//        Boolean checkExists = categoryRepository.existsById(getID.getId());
//        Assert.assertTrue("categoryShouldNULL Should Null",checkExists == false);
//    }
//
//    @After
//    public void dropTableByMyself(){
//        categoryRepository.deleteAll();
//    }
//}
