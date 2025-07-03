package com.allboard.verification.email.service;

import com.allboard.verification.common.model.Identifier;
import com.allboard.verification.common.model.Info;
import com.allboard.verification.common.service.InfoAuthenticator;

public final class EmailInfoAuthenticator implements InfoAuthenticator {
    @Override
    public boolean authenticate(Info inputInfo, Info issuedInfo) {
        Identifier inputIdentifier = inputInfo.getIdentifier();
        Identifier issuedIdentifier = issuedInfo.getIdentifier();

        String inputEmail = inputIdentifier.getValue();
        String issuedEmail = issuedIdentifier.getValue();

        String inputCode = inputInfo.getCode();
        String issuedCode = issuedInfo.getCode();

        boolean isAuthenticated = (inputEmail.equals(issuedEmail) && inputCode.equals(issuedCode));

        return isAuthenticated;
    }
}
