package sms.allBoard.Common.Service.Auth.Verification.Authenticator;

import org.springframework.stereotype.Component;
import sms.allBoard.Common.Service.Auth.Verification.Identifier.Identifier;
import sms.allBoard.Common.Service.Auth.Verification.Info.VerificationInfo;

@Component("emailVerificationAuthenticator")
public final class EmailVerificationAuthenticator implements VerificationAuthenticator {
    @Override
    public boolean authenticate(VerificationInfo inputInfo, VerificationInfo issuedInfo) {
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
