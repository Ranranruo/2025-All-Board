package sms.allBoard.Common.Service.Verification.Authenticator;

import sms.allBoard.Common.Service.Verification.Model.Info.VerificationInfo;

public interface VerificationAuthenticator {
    boolean authenticate(VerificationInfo inputInfo, VerificationInfo issuedInfo);
}
