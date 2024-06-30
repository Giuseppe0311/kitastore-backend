package com.ecommerce.products.dto;

import com.ecommerce.products.models.Category;

public record ProductDTO(
        String _id,
        String name,
        String description,
        Double price,
        String status,
        Category category
) {
}
