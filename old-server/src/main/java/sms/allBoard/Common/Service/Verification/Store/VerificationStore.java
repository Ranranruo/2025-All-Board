package sms.allBoard.Common.Service.Verification.Store;

import sms.allBoard.Common.Service.Verification.Model.Info.VerificationInfo;

public interface VerificationStore {
    VerificationInfo get();
    void set(VerificationInfo info);
    void delete();
}
