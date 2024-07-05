package com.ecommerce.order.dto;

import java.math.BigDecimal;

public record PurchaseResponse(
        String id,
        String name,
        String description,
        BigDecimal price,
        Integer quantity
) {
}
