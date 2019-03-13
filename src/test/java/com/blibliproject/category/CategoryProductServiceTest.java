package com.blibliproject.category;

import com.blibliproject.category.model.CategoryHasProduct;
import com.blibliproject.category.repository.CategoryProductRepository;
import com.blibliproject.category.service.CategoryProductServiceImplementation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;

public class CategoryProductServiceTest {

    private CategoryProductServiceImplementation categoryProductService;

    private CategoryProductRepository categoryProductRepository;

    @Before
    public void setUp() throws Exception{
        categoryProductRepository = Mockito.mock(CategoryProductRepository.class);
        categoryProductService = new CategoryProductServiceImplementation(categoryProductRepository);
    }

    @Test
    public void testCreate(){
        CategoryHasProduct data = new CategoryHasProduct(
            null,
            "1",
            "2"
        );

        CategoryHasProduct dataReturn = new CategoryHasProduct(
            "anyGeneratedKey",
            "1",
            "2"
        );

        Mockito.when(categoryProductRepository.save(data)).thenReturn(Mono.just(dataReturn));

        CategoryHasProduct expectedReturnData = categoryProductService.create(data).block();

        Assert.assertNotNull("ID is Not NULL",dataReturn.getId());
        Assert.assertTrue("expectedReturnData has productID of 1",dataReturn.getProductID().equals("1"));
        Assert.assertTrue("expectedReturnData has categoryID of 2",dataReturn.getCategoryID().equals("2"));

    }

}
