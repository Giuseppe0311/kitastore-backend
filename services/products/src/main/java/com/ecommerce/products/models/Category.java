package com.ecommerce.products.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    private String _id;
    private String name;
    private String description;
    private String status;
}
