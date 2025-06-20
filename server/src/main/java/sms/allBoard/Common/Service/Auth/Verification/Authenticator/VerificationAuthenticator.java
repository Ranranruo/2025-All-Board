package sms.allBoard.Common.Service.Auth.Verification.Authenticator;

import sms.allBoard.Common.Service.Auth.Verification.Info.VerificationInfo;

public interface VerificationAuthenticator {
    boolean authenticate(VerificationInfo inputInfo, VerificationInfo issuedInfo);
}
