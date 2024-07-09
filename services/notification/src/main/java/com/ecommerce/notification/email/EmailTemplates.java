package com.ecommerce.notification.email;

import lombok.Getter;

@Getter
public enum EmailTemplates {
    PURCHASE_CONFIRMATION("purchaseConfirmation.html", "Purchase Confirmation"),
    ORDER_CONFIRMATION("order-confirmation.html", "Order Confirmation");

    private final String template;
    private final String subject;

    EmailTemplates(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }
}
