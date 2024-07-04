package com.ecommerce.products.dto;


public record ProductPurchaseDTO(
        String id,
        String name,
        String description,
        Double price,
        Integer quantity
) {
}
