package com.allboard.auth.dto;

import com.github.ranranruo.Response.FieldStatus;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailVerificationCodeResponseDTO {
    private FieldStatus email;
}