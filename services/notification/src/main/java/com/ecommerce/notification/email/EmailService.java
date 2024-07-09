package com.ecommerce.notification.email;

import com.ecommerce.notification.kafka.Purchase;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.utils.Java;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sendOrderConfirmationEmail(
            String destinationEmail,
            String customerName,
            BigDecimal totalAmount,
            String orderReference,
            List<Purchase> products
    ) throws MessagingException {
        log.info("Sending order confirmation email to {}", destinationEmail);
        final String templateName = EmailTemplates.ORDER_CONFIRMATION.getTemplate();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,"UTF-8");
        messageHelper.setSubject(EmailTemplates.ORDER_CONFIRMATION.getSubject());

        //configurar las variables del template
        Map<String,Object> variables = new HashMap<>();
        variables.put("customerName",customerName);
        variables.put("totalAmount",totalAmount);
        variables.put("orderReference",orderReference);
        variables.put("products",products);
        Context context = new Context();
        context.setVariables(variables);
        try {
            String html  = templateEngine.process(templateName,context);
            messageHelper.setText(html,true);
            messageHelper.setTo(destinationEmail);
            javaMailSender.send(mimeMessage);
            log.info("Email sent successfully to {}",destinationEmail);
        }catch (MessagingException e){
            log.error("Error sending email to {}",destinationEmail,e);
            throw e;
        }

    }
}
