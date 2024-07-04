package com.ecommerce.order.services;

import com.ecommerce.order.dto.OrdersDto;
import com.ecommerce.order.mapper.OrdersMapper;
import com.ecommerce.order.models.Orders;
import com.ecommerce.order.repository.OrderRepository;
import com.ecommerce.order.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderRepository orderRepository;
    private final OrdersMapper orderMapper;

    @Override
    public List<OrdersDto> getOrders() {
        return List.of();
    }

    @Override
    public OrdersDto createOrder(OrderRequest request) {
        //CUSTOMER
        //todo: validate the customer id
        Orders orders = orderMapper.toEntity(request);
        Orders orderCreated=  orderRepository.save(orders);
        return orderMapper.toDto(orderCreated);
    }

}
