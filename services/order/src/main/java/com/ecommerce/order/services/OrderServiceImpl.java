package com.ecommerce.order.services;

import com.ecommerce.order.client.ProductFeignClient;
import com.ecommerce.order.dto.OrdersDto;
import com.ecommerce.order.dto.PurchaseResponse;
import com.ecommerce.order.exception.FeingException;
import com.ecommerce.order.exception.OrderAmountException;
import com.ecommerce.order.mapper.OrdersMapper;
import com.ecommerce.order.models.OrderDetail;
import com.ecommerce.order.models.Orders;
import com.ecommerce.order.repository.OrderRepository;
import com.ecommerce.order.request.OrderDetailRequest;
import com.ecommerce.order.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrdersMapper orderMapper;
    private final OrderDetailService orderDetailService;
    private final ProductFeignClient productFeignClient;

    @Override
    public List<OrdersDto> getOrders() {
        return List.of();
    }

    @Override
    @Transactional
    public OrdersDto createOrder(OrderRequest request) {
        //CUSTOMER
        //todo: validate the customer id
        List<PurchaseResponse> purchaseProducts = productFeignClient.purchaseProducts(request.products());
        if (purchaseProducts == null || purchaseProducts.isEmpty())
            throw new FeingException("Error while purchasing products");

//        validamos si realmente el total de compra es igual al total de la suma de los productos

        BigDecimal auxTotalAmount = purchaseProducts.stream()
                .map(purchaseResponse -> purchaseResponse.price().multiply(BigDecimal.valueOf(purchaseResponse.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        if ( auxTotalAmount.compareTo(request.totalAmount()) != 0){
            throw new OrderAmountException("The total amount is not equal to the sum of the products");
        }
        Orders orders = orderMapper.toEntity(request);
        orderRepository.save(orders);
        for (PurchaseResponse purchaseResponse : purchaseProducts) {
            BigDecimal subTotal = purchaseResponse.price().multiply(BigDecimal.valueOf(purchaseResponse.quantity()));
            OrderDetailRequest orderDetailRequest = new OrderDetailRequest(
                    orders.getId(),
                    purchaseResponse.id(),
                    subTotal,
                    purchaseResponse.quantity()
            );
            orderDetailService.createOrderDetail(orderDetailRequest);
        }
        return orderMapper.toDto(orders);
    }

}
