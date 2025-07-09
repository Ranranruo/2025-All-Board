package com.allboard.auth.service;

import com.allboard.auth.dto.SignUpRequestDTO;

public interface AuthService {
    boolean isExistsUsername(String username);
    boolean isExistsEmail(String email);
    void signUp(SignUpRequestDTO signUpRequestDTO);
}