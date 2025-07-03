package com.allboard.verification.email.service.impl;

import com.allboard.verification.common.model.Info;
import com.allboard.verification.common.service.InfoAuthenticator;
import org.springframework.stereotype.Component;

@Component
public final class EmailInfoAuthenticator implements InfoAuthenticator {
    @Override
    public boolean authenticate(Info inputInfo, Info issuedInfo) {
        String inputEmail = inputInfo.getIdentifier();
        String issuedEmail = issuedInfo.getIdentifier();

        String inputCode = inputInfo.getCode();
        String issuedCode = issuedInfo.getCode();

        boolean isAuthenticated = (inputEmail.equals(issuedEmail) && inputCode.equals(issuedCode));

        return isAuthenticated;
    }
}
