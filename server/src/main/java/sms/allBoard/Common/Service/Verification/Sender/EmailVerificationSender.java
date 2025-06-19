package sms.allBoard.Common.Service.Verification.Sender;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sms.allBoard.Common.Service.Verification.Identifier.Identifier;
import sms.allBoard.Common.Service.Verification.Info.VerificationInfo;
import sms.allBoard.Common.Util.MailService;

@Component("emailVerificationSender")
@RequiredArgsConstructor
public final class EmailVerificationSender implements VerificationSender {
    private final MailService mailService;
    @Override
    public void send(VerificationInfo info) {
        Identifier identifier = info.getIdentifier();

        String code = info.getCode();
        String email = identifier.getValue();

        mailService.sendText(
                email,
                "Verification for sign-up",
                "your code: " + code
        );
    }
}
