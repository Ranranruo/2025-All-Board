package sms.allBoard.Common.Service.Verification.Impl;

import sms.allBoard.Common.Service.Verification.Info.VerificationInfo;
import sms.allBoard.Common.Service.Verification.VerificationService;

public final class EmailVerificationService implements VerificationService {

    @Override
    public void send(VerificationInfo verificationInfo) {

    }

    @Override
    public boolean authenticate(VerificationInfo verificationInfo) {
        return false;
    }
}
