package com.allboard.auth.feign.verification.dto;


import lombok.Data;

@Data
public class EmailVerificationRequestDTO {
    private String email;
    private String code;
}
