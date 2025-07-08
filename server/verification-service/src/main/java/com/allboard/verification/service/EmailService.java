package com.allboard.verification.service;

import com.allboard.verification.common.vo.Info;

public interface EmailService {
    public void sendCode(String email);
    public boolean authenticate(Info info);
}
