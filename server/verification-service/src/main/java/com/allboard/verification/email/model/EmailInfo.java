package com.allboard.verification.email.model;

import com.allboard.verification.common.model.Info;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public final class EmailInfo implements Info {
    String email;
    String code;
    @Override
    public String getIdentifier() {
        return this.email;
    }

    @Override
    public String getCode() {
        return this.code;
    }
}
