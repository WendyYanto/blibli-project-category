package com.blibliproject.category.listener;

import com.blibliproject.category.model.CategoryHasProduct;
import com.blibliproject.category.service.CategoryProductService;
import com.blibliproject.category.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class ProductsCreatedListener {

    @Autowired
    private CategoryProductService categoryProductService;

    @Autowired
    private ObjectMapper objectMapper;

    @KafkaListener(topics = "products_created")
    public void receiveProductCreatedNotice(String body) throws IOException {
        CategoryHasProduct insertData = objectMapper.readValue(body,CategoryHasProduct.class);
        categoryProductService.create(insertData).block();
    }
}
