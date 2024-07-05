package com.ecommerce.order.services;

import com.ecommerce.order.exception.OrderNotFoundException;
import com.ecommerce.order.mapper.OrderDetailMapper;
import com.ecommerce.order.models.OrderDetail;
import com.ecommerce.order.models.Orders;
import com.ecommerce.order.repository.OrderDetailRepository;
import com.ecommerce.order.request.OrderDetailRequest;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {
    private final OrderDetailMapper orderDetailMapper;
    private final OrderDetailRepository orderDetailRepository;

    @Override
    public void createOrderDetail(OrderDetailRequest request) {
        OrderDetail orderDetail = orderDetailMapper.toEntity(request);
        orderDetail.setOrders(Orders.builder()
                .id(request.orderId())
                .build());
        orderDetailRepository.save(orderDetail);
    }
}
