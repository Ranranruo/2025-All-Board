package sms.allBoard.Common.Service.Verification.Generator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sms.allBoard.Common.Util.RandomStringGenerator;

@Component("emailVerificationGenerator")
@RequiredArgsConstructor
public final class EmailVerificationGenerator implements VerificationGenerator {
    private final RandomStringGenerator randomStringGenerator;
    @Override
    public String generate() {
        return randomStringGenerator.generate(6, true, true, true);
    }
}
