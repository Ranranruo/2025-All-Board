package com.allboard.verification.email;

import com.allboard.verification.email.dto.EmailVerificationDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public final class EmailConsumer {
    @RabbitListener(queues = "verification.email")
    public void verificationEmail(EmailVerificationDTO message) {
        log.info(message.email);
    }
}
