package com.allboard.auth.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailVerificationCodeRequestDTO {
    private String email;
}