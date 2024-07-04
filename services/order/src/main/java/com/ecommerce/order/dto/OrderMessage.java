package com.ecommerce.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public record OrderMessage(
        String message,
        Object response,
        LocalDateTime time
) {
}
