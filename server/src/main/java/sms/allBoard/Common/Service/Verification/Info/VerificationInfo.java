package sms.allBoard.Common.Service.Verification.Info;

import sms.allBoard.Common.Service.Verification.Identifier.Identifier;

public interface VerificationInfo {
    Identifier getIdentifier();
    String getCode();
}
