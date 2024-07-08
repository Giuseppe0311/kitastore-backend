package com.ecommerce.order.events;

import com.ecommerce.order.kafka.OrderConfirmationRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderEventsService {

    private final KafkaTemplate<String,Object> kafkaTemplate;

    public void sendOrderEvent(OrderConfirmationRequest orderconfirmation){
        log.info("Sending order confirmation event {}", orderconfirmation);
        Message<OrderConfirmationRequest> message = MessageBuilder
                .withPayload(orderconfirmation)
                .setHeader(KafkaHeaders.TOPIC,"order-topic")
                .build();
        kafkaTemplate.send(message);
    }
}
