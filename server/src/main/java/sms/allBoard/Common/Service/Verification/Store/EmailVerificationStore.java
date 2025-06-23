package sms.allBoard.Common.Service.Verification.Store;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import sms.allBoard.Common.Service.Verification.Model.Info.VerificationInfo;

@RequiredArgsConstructor
@Component("emailVerificationStore")
public final class EmailVerificationStore implements VerificationStore {
    private final HttpSession session;

    @Override
    public VerificationInfo get() {
        return (VerificationInfo) session.getAttribute("verificationInfo");
    }

    @Override
    public void set(VerificationInfo info) {
        session.setAttribute("verificationInfo", info);
    }

    @Override
    public void delete() {
        session.removeAttribute("verificationInfo");
    }
}
