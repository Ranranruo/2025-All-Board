package sms.allBoard.Common.Service.Verification.Authenticator;

import sms.allBoard.Common.Service.Verification.Info.VerificationInfo;

public interface VerificationAuthenticator {
    boolean authenticate(VerificationInfo verificationInfo);
}
