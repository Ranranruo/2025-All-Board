package sms.allBoard.Common.Service.Auth.Verification.Info;

import sms.allBoard.Common.Service.Auth.Verification.Identifier.Identifier;

public interface VerificationInfo {
    Identifier getIdentifier();
    String getCode();
}
