package com.allboard.verification.email.service.impl;

import com.allboard.verification.common.vo.Info;
import com.allboard.verification.common.service.CodeSender;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class EmailCodeSender implements CodeSender {
    private final JavaMailSender mailSender;

    @Override
    public void send(Info info) {
        SimpleMailMessage message = new SimpleMailMessage();

        String code = info.getCode();
        String email = info.getIdentifier();

        message.setTo(email);
        message.setSubject("Verification for sign-up");
        message.setText("your code: " + code);

        mailSender.send(message);
    }
}
