package com.allboard.auth.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDTO {
    private String username;
    private String password;
    private String email;
    private String displayName;
    private String verificationCode;
}
