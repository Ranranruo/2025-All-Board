package com.allboard.verification.service.impl;

import com.allboard.verification.common.vo.Info;
import com.allboard.verification.common.service.InfoVerifier;
import org.springframework.stereotype.Component;

@Component
public final class EmailInfoVerifier implements InfoVerifier {
    @Override
    public boolean verify(Info inputInfo, Info issuedInfo) {
        String inputEmail = inputInfo.getIdentifier();
        String issuedEmail = issuedInfo.getIdentifier();

        String inputCode = inputInfo.getCode();
        String issuedCode = issuedInfo.getCode();

        boolean isAuthenticated = (inputEmail.equals(issuedEmail) && inputCode.equals(issuedCode));

        return isAuthenticated;
    }
}
