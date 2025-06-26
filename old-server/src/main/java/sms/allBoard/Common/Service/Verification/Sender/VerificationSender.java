package sms.allBoard.Common.Service.Verification.Sender;

import sms.allBoard.Common.Service.Verification.Model.Info.VerificationInfo;

public interface VerificationSender {
    void send(VerificationInfo info);
}
