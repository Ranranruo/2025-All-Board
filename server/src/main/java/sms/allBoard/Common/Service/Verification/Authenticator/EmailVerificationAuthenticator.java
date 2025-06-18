package sms.allBoard.Common.Service.Verification.Authenticator;

import sms.allBoard.Common.Service.Verification.Info.VerificationInfo;

public final class EmailVerificationAuthenticator implements VerificationAuthenticator {
    @Override
    public boolean authenticate(VerificationInfo verificationInfo) {
        return false;
    }
}
