package com.ecommerce.order.services;

import com.ecommerce.order.dto.OrdersDto;
import com.ecommerce.order.request.OrderRequest;

import java.util.List;

public interface OrderService {
    List<OrdersDto> getOrders();
    OrdersDto createOrder(OrderRequest request);
}
