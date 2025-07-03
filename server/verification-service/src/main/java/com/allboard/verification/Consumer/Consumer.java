package com.allboard.verification.Consumer;

import com.allboard.verification.DTO.EmailVerificationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Consumer {
    @RabbitListener(queues = "verification.email")
    public void verificationEmail(EmailVerificationDTO message) {
        log.info(message.email);
    }
}
