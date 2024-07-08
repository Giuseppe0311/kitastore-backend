package com.ecommerce.order.services;

import com.ecommerce.order.client.ProductFeignClient;
import com.ecommerce.order.dto.OrdersDto;
import com.ecommerce.order.dto.PurchaseResponse;
import com.ecommerce.order.events.OrderEventsService;
import com.ecommerce.order.exception.FeingException;
import com.ecommerce.order.exception.OrderAmountException;
import com.ecommerce.order.mapper.OrdersMapper;
import com.ecommerce.order.models.Orders;
import com.ecommerce.order.repository.OrderRepository;
import com.ecommerce.order.kafka.OrderConfirmationRequest;
import com.ecommerce.order.request.OrderDetailRequest;
import com.ecommerce.order.request.OrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrdersMapper orderMapper;
    private final OrderDetailService orderDetailService;
    private final ProductFeignClient productFeignClient;
    private final OrderEventsService orderEventsService;
    @Override
    public List<OrdersDto> getOrders() {
        return List.of();
    }

    @Override
    @Transactional
    public OrdersDto createOrder(OrderRequest request) {
        //CUSTOMER
        //todo: validate the customer id
        log.info("Obteniendo el producto");
        List<PurchaseResponse> purchaseProducts = productFeignClient.purchaseProducts(request.products());
        if (purchaseProducts == null || purchaseProducts.isEmpty())
            throw new FeingException("Error while checking purchasing products");

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
        //LANZAMOS EL EVENTO DE ORDEN CREADA

        orderEventsService.sendOrderEvent(new OrderConfirmationRequest(
               request.reference(),
                request.totalAmount(),
                purchaseProducts,
                request.customerId(),
                request.paymentMethod()
        ));


        return orderMapper.toDto(orders);
    }

}
