package com.ecommerce.order.request;

import com.ecommerce.order.models.PaymentMethod;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
        @NotNull
        @NotBlank
        String reference,
        @Positive
        @NotNull
        BigDecimal totalAmount,
        @NotNull
        PaymentMethod paymentMethod,
        @Valid
        @NotNull
        List<ProductPurchaseRequest> products,
        @NotNull
        @NotBlank
        String customerId
) {

}
