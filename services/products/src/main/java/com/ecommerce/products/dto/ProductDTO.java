package com.ecommerce.products.dto;

import com.ecommerce.products.models.Category;

import java.math.BigDecimal;

public record ProductDTO(
        String _id,
        String name,
        String description,
        BigDecimal price,
        String status,
        Category category
) {
}
