package sms.allBoard.Common.Service.Verification.Identifier;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class EmailIdentifier implements Identifier {
    private String email;
    public EmailIdentifier(String email) {
        this.email = email;
    }
    @Override
    public String getValue() {
        return this.email;
    }
}
