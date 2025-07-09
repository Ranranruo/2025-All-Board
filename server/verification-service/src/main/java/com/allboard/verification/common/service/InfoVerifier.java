package com.allboard.verification.common.service;

import com.allboard.verification.common.vo.Info;

public interface InfoVerifier {
    boolean verify(Info inputInfo, Info issuedInfo);
}
