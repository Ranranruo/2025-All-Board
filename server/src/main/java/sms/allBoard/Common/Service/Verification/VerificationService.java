package sms.allBoard.Common.Service.Verification;

import sms.allBoard.Common.Service.Verification.Info.VerificationInfo;

public interface VerificationService {
    void send(VerificationInfo verificationInfo);
    boolean authenticate(VerificationInfo verificationInfo);
}
