package com.ecommerce.products.request;

import com.ecommerce.products.models.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest{
    String _id;
    String name;
    String description;
    Double price;
    String status;
    Category category;
}
