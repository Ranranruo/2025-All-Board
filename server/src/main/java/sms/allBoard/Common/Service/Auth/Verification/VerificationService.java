package sms.allBoard.Common.Service.Auth.Verification;

import sms.allBoard.Common.Service.Auth.Verification.Identifier.Identifier;
import sms.allBoard.Common.Service.Auth.Verification.Info.VerificationInfo;

public interface VerificationService {
    void send(Identifier identifier);
    boolean authenticate(VerificationInfo verificationInfo);
}
