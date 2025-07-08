package com.allboard.verification.consumer;

import com.allboard.verification.dto.EmailCodeRequestDTO;
import com.allboard.verification.service.EmailService;
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
