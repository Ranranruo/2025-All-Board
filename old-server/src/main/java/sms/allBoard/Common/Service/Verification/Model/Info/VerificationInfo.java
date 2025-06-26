package sms.allBoard.Common.Service.Verification.Model.Info;

import sms.allBoard.Common.Service.Verification.Model.Identifier.Identifier;

public interface VerificationInfo {
    Identifier getIdentifier();
    String getCode();
}
