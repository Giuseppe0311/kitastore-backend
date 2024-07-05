package com.ecommerce.order.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductPurchaseRequest
        (
                @NotNull
                @NotBlank
                String productId,
                @Positive
                @NotNull
                Integer quantity
        ) {
}
