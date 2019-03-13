package com.blibliproject.category;

import com.blibliproject.category.model.Category;
import com.blibliproject.category.repository.CategoryRepository;
import com.blibliproject.category.service.CategoryServiceImplementation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class CategoryServiceTest {

    private CategoryServiceImplementation categoryService;

    private CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception{
        categoryRepository = Mockito.mock(CategoryRepository.class);
        categoryService = new CategoryServiceImplementation(categoryRepository);
    }

    @Test
    public void expectCategoryServiceCreateIsWorking(){
        Category firstCategory = new Category(
            "1",
            "Minuman"
        );

        List<Category> lists = new ArrayList<Category>();
        lists.add(firstCategory);

        Mockito.when(categoryRepository.save(firstCategory)).thenReturn(Mono.just(firstCategory));
        Mockito.when(categoryRepository.findAll()).thenReturn(Flux.fromIterable(lists));
        Mono<Category> insertedFirstCategory = categoryService.create(firstCategory);

        List<Category> flux_lists = categoryService.getAll().collectList().block();

        Assert.assertTrue("firstCategory Is Inserted",firstCategory.getName() == insertedFirstCategory.block().getName());
        Assert.assertTrue("flux_lists return size of 1",flux_lists.size() == 1);
    }

    @Test
    public void expectCategoryGetAllServiceIsWorking(){
        Category firstCategory = new Category(
            "1",
            "Minuman"
        );

        Category secondCategory = new Category(
            "2",
            "Makanan"
        );

        List<Category> lists = new ArrayList<Category>();
        lists.add(firstCategory);
        lists.add(secondCategory);

        Mockito.when(categoryRepository.findAll()).thenReturn(Flux.fromIterable(lists));

        List<Category> return_lists = categoryService.getAll().collectList().block();

        Assert.assertTrue("flux_lists return size of 2",return_lists.size() == 2);
    }

    @Test
    public void expectCategoryFindByIDServiceIsWorking(){
        Category firstCategory = new Category(
            "1",
            "Minuman"
        );

        Category secondCategory = new Category(
            "2",
            "Makanan"
        );

        Mockito.when(categoryRepository.findById("1")).thenReturn(Mono.just(firstCategory));
        Mockito.when(categoryRepository.findById("2")).thenReturn(Mono.just(secondCategory));
        Mockito.when(categoryRepository.findById("3")).thenReturn(Mono.empty());

        Category isNotNull = categoryService.findById("1").block();
        Category isNull = categoryService.findById("3").block();
        Category secondData = categoryService.findById("2").block();

        Assert.assertTrue("Must Return Same Name",isNotNull.getName().equals(firstCategory.getName()));
        Assert.assertNull("Must Return Null",isNull);
        Assert.assertNotNull("Must Return Not NUll",secondData);
    }

    @Test
    public void expectCategoryServiceUpdateIsWorking(){
        Category firstCategory = new Category(
            "1",
            "Minuman"
        );

        Category updateCategory = new Category(
            "1",
            "Makanan"
        );

        Mockito.when(categoryRepository.save(firstCategory)).thenReturn(Mono.just(firstCategory));
        Mockito.when(categoryRepository.save(updateCategory)).thenReturn(Mono.just(updateCategory));
        Mockito.when(categoryRepository.findById("1")).thenReturn(Mono.just(updateCategory));

        categoryService.create(firstCategory).block();
        Category updateCategoryReturn = categoryService.update(updateCategory,"1").block();

        Assert.assertTrue("Must Return Updated Data",updateCategory.getName().equals("Makanan"));
        Assert.assertTrue("Must Return the Same ID",updateCategory.getId().equals("1"));
    }

    @Test
    public void expectCategoryServiceDeleteIsWorking(){
        Category firstCategory = new Category(
            "1",
            "Minuman"
        );

        Mockito.when(categoryRepository.save(firstCategory)).thenReturn(Mono.just(firstCategory));
        Mockito.when(categoryRepository.deleteById("1")).thenReturn(null);
        Mockito.when(categoryRepository.deleteById("2")).thenReturn(null);
        Mockito.when(categoryRepository.findById("1")).thenReturn(Mono.just(firstCategory));
        Mockito.when(categoryRepository.findById("2")).thenReturn(Mono.empty());
        Mockito.when(categoryRepository.findAll()).thenReturn(Flux.empty());

        categoryService.create(firstCategory).block();

        List<Category> current_lists = categoryService.getAll().collectList().block();
        Category deletedData = categoryService.delete("1").block();
        Category expectedReturnNULL = categoryService.delete("2").block();

        Assert.assertTrue("currentLists must Return Size of 0",current_lists.size() == 0);
        Assert.assertTrue("deletedData must return ID of 1",deletedData.getId().equals("1"));
        Assert.assertNull("expectedReturnNULL must be NULL",expectedReturnNULL);
    }
}
