package sms.allBoard.Auth.Service.Impl;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sms.allBoard.Auth.Service.VerificationService;
import sms.allBoard.Common.Enum.FieldStatus;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class VerificationServiceImpl implements VerificationService {
    private final HttpSession session;

    public String generateVerificationCode() {
        int length = 6;
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < length; i++) {
            code.append(random.nextInt(10));
        }
        return code.toString();
    }

    public void saveVerificationSession(String email, String code) {
        session.setAttribute("verificationEmail", email);
        session.setAttribute("verificationCode", code);
    }

    public FieldStatus verifyVerificationCode(String email, String verificationCode) {
        if(
                email.equals(session.getAttribute("verificationEmail"))
                        && verificationCode.equals(session.getAttribute("verificationCode"))
        ) {
            return FieldStatus.SUCCESS;
        } else {
            return FieldStatus.INVALID;
        }
    }
}
