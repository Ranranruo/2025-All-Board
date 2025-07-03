package com.allboard.verification.email.service;

import com.allboard.verification.common.model.Identifier;
import com.allboard.verification.common.model.Info;
import com.allboard.verification.common.service.CodeSender;
import com.allboard.verification.common.util.MailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class EmailCodeSender implements CodeSender {
    private final MailUtil mailUtil;

    @Override
    public void send(Info info) {
        Identifier identifier = info.getIdentifier();

        String code = info.getCode();
        String email = identifier.getValue();

        mailUtil.sendText(
                email,
                "Verification for sign-up",
                "your code: " + code
        );
    }
}
