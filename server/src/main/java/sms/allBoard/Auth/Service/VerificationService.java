package sms.allBoard.Auth.Service;

import sms.allBoard.Common.Enum.FieldStatus;

public interface VerificationService {
    String generateVerificationCode();
    void saveVerificationSession(String email, String code);
    FieldStatus verifyVerificationCode(String email, String verificationCode);
}
