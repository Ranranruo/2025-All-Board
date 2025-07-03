package com.allboard.verification.email.domain;

import com.allboard.verification.common.domain.Identifier;
import com.allboard.verification.common.domain.Info;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public final class EmailInfo implements Info {
    Identifier identifier;
    String code;
    @Override
    public Identifier getIdentifier() {
        return this.identifier;
    }

    @Override
    public String getCode() {
        return this.code;
    }
}
