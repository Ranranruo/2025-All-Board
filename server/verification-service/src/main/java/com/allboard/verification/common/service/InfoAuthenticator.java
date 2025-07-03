package com.allboard.verification.common.service;

import com.allboard.verification.common.vo.Info;

public interface InfoAuthenticator {
    boolean authenticate(Info inputInfo, Info issuedInfo);
}
