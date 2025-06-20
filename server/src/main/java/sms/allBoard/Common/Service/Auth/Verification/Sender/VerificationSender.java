package sms.allBoard.Common.Service.Auth.Verification.Sender;

import sms.allBoard.Common.Service.Auth.Verification.Info.VerificationInfo;

public interface VerificationSender {
    void send(VerificationInfo info);
}
