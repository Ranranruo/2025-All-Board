package sms.allBoard.Common.Service.Verification;

import sms.allBoard.Common.Service.Verification.Identifier.Identifier;
import sms.allBoard.Common.Service.Verification.Info.VerificationInfo;

public interface VerificationService {
    void send(Identifier identifier);
    boolean authenticate(VerificationInfo verificationInfo);
}
