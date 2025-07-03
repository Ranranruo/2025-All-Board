package com.allboard.verification.email.repository.impl;

import com.allboard.verification.common.model.Info;
import com.allboard.verification.common.service.InfoStore;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class EmailInfoStore implements InfoStore {
    private final HttpSession session;


    @Override
    public Info get() {
        return (Info) session.getAttribute("verificationInfo");
    }

    @Override
    public void set(Info info) {
        session.setAttribute("verificationInfo", info);
    }

    @Override
    public void delete() {
        session.removeAttribute("verificationInfo");
    }
}
