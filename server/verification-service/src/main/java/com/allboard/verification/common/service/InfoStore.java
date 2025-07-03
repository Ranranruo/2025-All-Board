package com.allboard.verification.common.service;

import com.allboard.verification.common.model.Info;

public interface Store {
    Info get();
    void set(Info info);
    void delete();
}
