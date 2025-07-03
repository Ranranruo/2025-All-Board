package com.allboard.verification.common.service;

import com.allboard.verification.common.model.Info;

public interface Authenticator {
    boolean authenticate(Info inputInfo, Info issuedInfo);
}
