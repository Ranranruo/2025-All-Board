package com.allboard.verification.common.service;

import com.allboard.verification.common.model.Info;

public interface MailSender {
    void send(Info info);
}
