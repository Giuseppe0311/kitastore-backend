package com.ecommerce.notification.service;

import com.ecommerce.notification.kafka.OrderConfirmationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomEventService {
    @KafkaListener(topics = "order-topic", groupId = "order-group-id")
    public void orderConfirmation(OrderConfirmationRequest orderConfirmationRequest){
        log.info("Prueba de que llega el objeto: {}", orderConfirmationRequest);
    }
}
