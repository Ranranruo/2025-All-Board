package sms.allBoard.Auth.Service;

import sms.allBoard.Auth.DTO.SignUpRequestDTO;

public interface AuthService {
    void signUp(SignUpRequestDTO signUpRequestDTO);
}
