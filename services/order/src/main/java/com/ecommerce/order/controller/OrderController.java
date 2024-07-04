package com.ecommerce.order.controller;

import com.ecommerce.order.dto.OrderMessage;
import com.ecommerce.order.dto.OrdersDto;
import com.ecommerce.order.request.OrderRequest;
import com.ecommerce.order.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
//    @GetMapping
//    public ResponseEntity<OrdersDto> getOrders(){
//        return ResponseEntity.ok().body(orderService.getOrders());
//    }

    @PostMapping
    public ResponseEntity<OrderMessage> createOrder(@RequestBody OrderRequest request){
        OrderMessage message = new OrderMessage("Order created", orderService.createOrder(request), LocalDateTime.now());
        return ResponseEntity.ok().body(message);
    }
}
