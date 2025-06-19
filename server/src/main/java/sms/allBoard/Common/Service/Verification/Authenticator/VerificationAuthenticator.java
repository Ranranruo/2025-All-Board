package sms.allBoard.Common.Service.Verification.Authenticator;

import sms.allBoard.Common.Service.Verification.Info.VerificationInfo;

public interface VerificationAuthenticator {
    boolean authenticate(VerificationInfo inputInfo, VerificationInfo issuedInfo);
}
