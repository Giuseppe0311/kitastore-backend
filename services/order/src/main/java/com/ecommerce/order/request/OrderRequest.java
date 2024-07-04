package com.ecommerce.order.request;

import com.ecommerce.order.models.PaymentMethod;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    String reference;
    BigDecimal totalAmount;
    PaymentMethod paymentMethod;
    List<ProductPurchaseRequest> products;
    String customerId;
}
