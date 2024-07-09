package com.ecommerce.notification.service;

import com.ecommerce.notification.email.EmailService;
import com.ecommerce.notification.kafka.OrderConfirmationRequest;
import com.ecommerce.notification.model.Notification;
import com.ecommerce.notification.model.NotificationType;
import com.ecommerce.notification.repository.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomEventService {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;
    @KafkaListener(topics = "order-topic", groupId = "order-group-id")
    public void orderConfirmation(OrderConfirmationRequest orderConfirmationRequest) throws MessagingException {
        log.info("Prueba de que llega el objeto: {}", orderConfirmationRequest);
        Notification notification =  new Notification(
                null,
                NotificationType.ORDER_CONFIRMATION,
                LocalDateTime.now(),
                orderConfirmationRequest
        );
        notificationRepository.save(notification);
        // I'M STILL WORKKIN ON THIS, I FORGET TO ADD THE  CUSTOMER MODEL ON THE PROJECT
        emailService.sendOrderConfirmationEmail(
               "SOME EMAIL",
                "SOME NAME",
                orderConfirmationRequest.amount(),
                orderConfirmationRequest.reference(),
                orderConfirmationRequest.purchasedProducts()
        );
        log.info("Order confirmation email sent successfully");
    }
}
