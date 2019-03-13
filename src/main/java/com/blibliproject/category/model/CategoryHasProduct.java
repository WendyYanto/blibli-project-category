package com.blibliproject.category.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "categoryRelations")
public class CategoryHasProduct {

    @Id
    private String id;

    private String productID;
    private String categoryID;
}
