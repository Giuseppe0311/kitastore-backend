package com.ecommerce.order.dto;

import com.ecommerce.order.models.PaymentMethod;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrdersDto(
        Integer id,
        String reference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        List<OrderDetailDto> orderDetails,
        String customerId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}