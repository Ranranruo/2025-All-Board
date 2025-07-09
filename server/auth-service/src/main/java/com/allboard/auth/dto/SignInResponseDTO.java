package com.allboard.auth.dto;

import com.github.ranranruo.Response.FieldStatus;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignInResponseDTO {
    private FieldStatus username;
    private FieldStatus password;
}