package com.ecommerce.products.dto;


import java.math.BigDecimal;

public record ProductPurchaseDTO(
        String id,
        String name,
        String description,
        BigDecimal price,
        Integer quantity
) {
}
