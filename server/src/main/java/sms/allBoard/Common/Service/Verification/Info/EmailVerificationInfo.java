package sms.allBoard.Common.Service.Verification.Info;

import lombok.Getter;
import lombok.Setter;
import sms.allBoard.Common.Service.Verification.Identifier.Identifier;

@Getter
@Setter
public final class EmailVerificationInfo implements VerificationInfo {
    private Identifier identifier;
    private String code;

    public EmailVerificationInfo(Identifier identifier, String code) {
        this.identifier = identifier;
        this.code = code;
    }

    @Override
    public Identifier getIdentifier() {
        return this.identifier;
    }
    @Override
    public String getCode() {
        return this.code;
    }
}
