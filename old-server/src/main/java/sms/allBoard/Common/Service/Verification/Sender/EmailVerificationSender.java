package sms.allBoard.Common.Service.Verification.Sender;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sms.allBoard.Common.Service.Verification.Model.Identifier.Identifier;
import sms.allBoard.Common.Service.Verification.Model.Info.VerificationInfo;
import sms.allBoard.Common.Util.MailUtil;

@Component("emailVerificationSender")
@RequiredArgsConstructor
public final class EmailVerificationSender implements VerificationSender {
    private final MailUtil mailUtil;
    @Override
    public void send(VerificationInfo info) {
        Identifier identifier = info.getIdentifier();

        String code = info.getCode();
        String email = identifier.getValue();

        mailUtil.sendText(
                email,
                "Verification for sign-up",
                "your code: " + code
        );
    }
}
