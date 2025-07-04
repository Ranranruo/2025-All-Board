package com.allboard.verification.email.consumer;

import com.allboard.verification.email.dto.EmailCodeRequestDTO;
import com.allboard.verification.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class EmailConsumer {
    private final EmailService emailService;
    @RabbitListener(queues = "verification.email")
    public void verificationEmail(EmailCodeRequestDTO message) {
        String email = message.getEmail();
        emailService.sendCode(email);
    }
}
