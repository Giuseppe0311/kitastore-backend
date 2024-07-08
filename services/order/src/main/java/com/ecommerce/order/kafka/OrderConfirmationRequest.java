package com.ecommerce.order.kafka;

import com.ecommerce.order.dto.PurchaseResponse;
import com.ecommerce.order.models.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmationRequest(
        String reference,
        BigDecimal amount,
        List<PurchaseResponse> purchasedProducts,
        String customerId,
        PaymentMethod paymentMethod
) {
}
