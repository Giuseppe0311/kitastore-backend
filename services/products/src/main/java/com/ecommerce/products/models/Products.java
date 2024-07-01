package com.ecommerce.products.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "products")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Products {
    @Id
    private String _id;
    private String name;
    private String description;
    private Double price;
    private String status;
    Category category;
}