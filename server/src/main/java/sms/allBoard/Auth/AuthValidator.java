package sms.allBoard.Auth;

import org.springframework.stereotype.Component;
import sms.allBoard.Auth.DTO.*;
import sms.allBoard.Common.Enum.FieldStatus;

@Component
public class AuthValidator {
    private final int MIN_USERNAME_LENGTH = 5;
    private final int MAX_USERNAME_LENGTH = 20;
    private final int MIN_DISPLAY_NAME_LENGTH = 2;
    private final int MAX_DISPLAY_NAME_LENGTH = 16;
    private final int MIN_PASSWORD_LENGTH = 8;
    private final int MAX_PASSWORD_LENGTH = 16;

    private final String USERNAME_REGEX = "^[a-zA-Z0-9]{5,20}$";
    private final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[!@#$%^&*()])[a-zA-Z0-9!@#$%^&*()]{8,16}$";
    private final String DISPLAY_NAME_REGEX = "^[a-zA-Z0-9가-힣]{2,16}$";
    private final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    private FieldStatus validateUsername(String username) {
        if(username == null || username.isBlank()) return FieldStatus.EMPTY;
        else if(username.length() < this.MIN_USERNAME_LENGTH) return FieldStatus.TOO_SHORT;
        else if(username.length() > this.MAX_USERNAME_LENGTH) return FieldStatus.TOO_LONG;
        else if (!username.matches(this.USERNAME_REGEX)) return FieldStatus.INVALID;
        return FieldStatus.SUCCESS;
    }

    private FieldStatus validateDisplayName(String displayName) {
        if(displayName == null || displayName.isBlank()) return FieldStatus.EMPTY;
        else if(displayName.length() < this.MIN_DISPLAY_NAME_LENGTH) return FieldStatus.TOO_SHORT;
        else if(displayName.length() > this.MAX_DISPLAY_NAME_LENGTH) return FieldStatus.TOO_LONG;
        else if (!displayName.matches(this.DISPLAY_NAME_REGEX)) return FieldStatus.INVALID;
        return FieldStatus.SUCCESS;
    }

    private FieldStatus validatePassword(String password) {
        if(password == null || password.isBlank()) return FieldStatus.EMPTY;
        else if(password.length() < this.MIN_PASSWORD_LENGTH) return FieldStatus.TOO_SHORT;
        else if(password.length() > this.MAX_PASSWORD_LENGTH) return FieldStatus.TOO_LONG;
        else if (!password.matches(this.PASSWORD_REGEX)) return FieldStatus.INVALID;
        return FieldStatus.SUCCESS;
    }

    private FieldStatus validateEmail(String email) {
        if(email == null || email.isBlank()) return FieldStatus.EMPTY;
        if(!email.matches(this.EMAIL_REGEX)) return FieldStatus.INVALID;
        return FieldStatus.SUCCESS;
    }

    public SignUpResponseDTO validateSignUpRequest(SignUpRequestDTO signUpRequestDTO) {

        SignUpResponseDTO signUpResponseDTO = new SignUpResponseDTO();

        String username = signUpRequestDTO.getUsername();
        String displayName = signUpRequestDTO.getDisplayName();
        String password = signUpRequestDTO.getPassword();
        String email = signUpRequestDTO.getEmail();

        signUpResponseDTO.setUsername(this.validateUsername(username));
        signUpResponseDTO.setDisplayName(this.validateDisplayName(displayName));
        signUpResponseDTO.setPassword(this.validatePassword(password));
        signUpResponseDTO.setEmail(this.validateEmail(email));

        return signUpResponseDTO;
    }

    public SignInResponseDTO validateSignInRequest(SignInRequestDTO signInRequestDTO) {
        SignInResponseDTO signInResponseDTO = new SignInResponseDTO();

        String username = signInRequestDTO.getUsername();
        String password = signInRequestDTO.getPassword();

        signInResponseDTO.setUsername(this.validateUsername(username));
        signInResponseDTO.setPassword(this.validatePassword(password));

        return signInResponseDTO;
    }

    public VerificationResponseDTO validateVerificationRequest(VerificationRequestDTO verificationRequestDTO) {
        VerificationResponseDTO verificationResponseDTO = new VerificationResponseDTO();

        String email = verificationRequestDTO.getEmail();

        verificationResponseDTO.setEmail(this.validateEmail(email));

        return verificationResponseDTO;
    }
}