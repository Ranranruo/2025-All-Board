package sms.allBoard.App.Auth.Service;

import sms.allBoard.App.Auth.DTO.SignUpRequestDTO;

public interface AuthService {
    boolean isExistsUsername(String username);
    boolean isExistsEmail(String email);
    void signUp(SignUpRequestDTO signUpRequestDTO);
}
