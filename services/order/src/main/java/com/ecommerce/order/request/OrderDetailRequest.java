package com.ecommerce.order.request;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record OrderDetailRequest(
        Integer orderId,
        String productId,
        BigDecimal subTotal,
        Integer quantity) {
}
