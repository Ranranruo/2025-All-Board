package sms.allBoard.Common.Service.Verification.Sender;

import sms.allBoard.Common.Service.Verification.Info.VerificationInfo;

public interface VerificationSender {
    void send(VerificationInfo verificationInfo);
}
