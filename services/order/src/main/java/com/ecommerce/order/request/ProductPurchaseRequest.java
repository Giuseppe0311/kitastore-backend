package com.ecommerce.order.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductPurchaseRequest
        (
                @NotNull
                String productId,
                @Positive
                Integer quantity
        ) {
}
