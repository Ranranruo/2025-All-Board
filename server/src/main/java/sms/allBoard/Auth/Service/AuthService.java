package sms.allBoard.Auth.Service;

import sms.allBoard.Auth.DTO.SignUpRequestDTO;

public interface AuthService {
    boolean isExistsUsername(String username);
    boolean isExistsEmail(String email);
    void signUp(SignUpRequestDTO signUpRequestDTO);
}
