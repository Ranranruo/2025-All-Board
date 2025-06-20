package sms.allBoard.Common.Service.Auth.Verification.Store;

import sms.allBoard.Common.Service.Auth.Verification.Info.VerificationInfo;

public interface VerificationStore {
    VerificationInfo get();
    void set(VerificationInfo info);
    void delete();
}
