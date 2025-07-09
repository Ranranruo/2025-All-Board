package com.allboard.auth.dto;

import com.github.ranranruo.Response.FieldStatus;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpResponseDTO {
    private FieldStatus username;
    private FieldStatus password;
    private FieldStatus email;
    private FieldStatus displayName;
    private FieldStatus verificationCode;

    public boolean isAllSuccess() {
        return (
                this.username.equals(FieldStatus.SUCCESS)
                && this.password.equals(FieldStatus.SUCCESS)
                && this.email.equals(FieldStatus.SUCCESS)
                && this.displayName.equals(FieldStatus.SUCCESS)
                && this.verificationCode.equals(FieldStatus.SUCCESS)
        );
    }
}
