package com.ecommerce.notification.kafka;

import java.math.BigDecimal;

public record Purchase(
        String id,
        String name,
        String description,
        BigDecimal price,
        Integer quantity

) {
}
