package com.ecommerce.notification.kafka;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmationRequest(
        String reference,
        BigDecimal amount,
        List<Purchase> purchasedProducts,
        String customerId,
        PaymentMethod paymentMethod
) {
}
