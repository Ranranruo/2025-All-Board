package com.allboard.verification.common.service;

import com.allboard.verification.common.model.Info;

public interface InfoStore {
    Info get(String identifier);
    void set(Info info);
    void delete(String identifier);
}
