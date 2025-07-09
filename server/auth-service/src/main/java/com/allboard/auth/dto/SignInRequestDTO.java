package com.allboard.auth.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequestDTO {
    private String username;
    private String password;
}