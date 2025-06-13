package sms.allBoard.Auth;

import sms.allBoard.Auth.DTO.SignUpRequestDTO;

public interface AuthService {
    boolean signUp(SignUpRequestDTO signUpRequestDTO);
}
