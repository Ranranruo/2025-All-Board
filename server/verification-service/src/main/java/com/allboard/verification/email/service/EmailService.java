package com.allboard.verification.email.service;

import com.allboard.verification.common.model.Info;

public interface EmailService {
    public void sendCode(String email);
    public boolean authenticate(Info info);
}
