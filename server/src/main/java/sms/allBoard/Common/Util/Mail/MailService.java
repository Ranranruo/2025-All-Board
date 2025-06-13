package sms.allBoard.Common.Util.Mail;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;

    public boolean sendVerifyMail(String email, String code) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("verification code");
            message.setText(code);
            message.setFrom("allBoard");
            mailSender.send(message);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("메일 전송 실패: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
