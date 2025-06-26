package sms.allBoard.Common.Service.Verification;

import sms.allBoard.Common.Service.Verification.Model.Identifier.Identifier;
import sms.allBoard.Common.Service.Verification.Model.Info.VerificationInfo;

public interface VerificationService {
    void send(Identifier identifier);
    boolean authenticate(VerificationInfo verificationInfo);
}
