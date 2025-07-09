package com.allboard.auth;

import com.allboard.auth.dto.*;
import com.github.ranranruo.Response.FieldStatus;
import org.springframework.stereotype.Component;

@Component
public class AuthValidator {
    private final int MIN_USERNAME_LENGTH = 5;
    private final int MAX_USERNAME_LENGTH = 20;
    private final int MIN_DISPLAY_NAME_LENGTH = 2;
    private final int MAX_DISPLAY_NAME_LENGTH = 16;
    private final int MIN_PASSWORD_LENGTH = 8;
    private final int MAX_PASSWORD_LENGTH = 16;

    // username은 영문/숫자 조합으로 5~20자
    private final String USERNAME_REGEX = "^[a-zA-Z0-9]{5,20}$";

    // password는 영문/숫자/특수문자(!@#$%^&*()) 포함 8~16자
    private final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[!@#$%^&*()])[a-zA-Z0-9!@#$%^&*()]{8,16}$";

    // displayName은 한글, 영문, 숫자 조합 2~16자
    private final String DISPLAY_NAME_REGEX = "^[a-zA-Z0-9가-힣]{2,16}$";
    
    // RFC 5322 기반의 일반적인 이메일 형식
    private final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    // username 유효성 검사
    private FieldStatus validateUsername(String username) {
        if(username == null || username.isBlank()) return FieldStatus.EMPTY;
        else if(username.length() < this.MIN_USERNAME_LENGTH) return FieldStatus.TOO_SHORT;
        else if(username.length() > this.MAX_USERNAME_LENGTH) return FieldStatus.TOO_LONG;
        else if (!username.matches(this.USERNAME_REGEX)) return FieldStatus.INVALID;
        return FieldStatus.SUCCESS;
    }

    // displayName 유효성 검사
    private FieldStatus validateDisplayName(String displayName) {
        if(displayName == null || displayName.isBlank()) return FieldStatus.EMPTY;
        else if(displayName.length() < this.MIN_DISPLAY_NAME_LENGTH) return FieldStatus.TOO_SHORT;
        else if(displayName.length() > this.MAX_DISPLAY_NAME_LENGTH) return FieldStatus.TOO_LONG;
        else if (!displayName.matches(this.DISPLAY_NAME_REGEX)) return FieldStatus.INVALID;
        return FieldStatus.SUCCESS;
    }

    // password 유효성 검사
    private FieldStatus validatePassword(String password) {
        if(password == null || password.isBlank()) return FieldStatus.EMPTY;
        else if(password.length() < this.MIN_PASSWORD_LENGTH) return FieldStatus.TOO_SHORT;
        else if(password.length() > this.MAX_PASSWORD_LENGTH) return FieldStatus.TOO_LONG;
        else if (!password.matches(this.PASSWORD_REGEX)) return FieldStatus.INVALID;
        return FieldStatus.SUCCESS;
    }

    // email 유효성 검사
    private FieldStatus validateEmail(String email) {
        if(email == null || email.isBlank()) return FieldStatus.EMPTY;
        if(!email.matches(this.EMAIL_REGEX)) return FieldStatus.INVALID;
        return FieldStatus.SUCCESS;
    }
    
    // verificationCode 유효성 검사
    private FieldStatus validateVerificationCode(String verificationCode) {
        if(verificationCode == null || verificationCode.isBlank()) return FieldStatus.EMPTY;
        return FieldStatus.SUCCESS;
    }

    // signUpRequest validation
    // 회원가입 요청 데이터 유효성 검사
    public SignUpResponseDTO validateSignUpRequest(SignUpRequestDTO requestBody) {
        SignUpResponseDTO responseBody = new SignUpResponseDTO();

        String username = requestBody.getUsername();
        String displayName = requestBody.getDisplayName();
        String password = requestBody.getPassword();
        String email = requestBody.getEmail();
        String verificationCode = requestBody.getVerificationCode();

        responseBody.setUsername(this.validateUsername(username));
        responseBody.setDisplayName(this.validateDisplayName(displayName));
        responseBody.setPassword(this.validatePassword(password));
        responseBody.setEmail(this.validateEmail(email));
        responseBody.setVerificationCode(this.validateVerificationCode(verificationCode));

        return responseBody;
    }

    
    // signInRequest validation
    // 로그인 요청 데이터 유효성 검사
    public SignInResponseDTO validateSignInRequest(SignInRequestDTO signInRequestDTO) {
        SignInResponseDTO signInResponseDTO = new SignInResponseDTO();

        String username = signInRequestDTO.getUsername();
        String password = signInRequestDTO.getPassword();

        signInResponseDTO.setUsername(this.validateUsername(username));
        signInResponseDTO.setPassword(this.validatePassword(password));

        return signInResponseDTO;
    }

    // verificationRequest validation
    // 인증 요청 데이터 유효성 검사
    public EmailVerificationCodeResponseDTO validateVerificationRequest(EmailVerificationCodeRequestDTO emailVerificationCodeRequestDTO) {
        EmailVerificationCodeResponseDTO emailVerificationCodeResponseDTO = new EmailVerificationCodeResponseDTO();

        String email = emailVerificationCodeRequestDTO.getEmail();

        emailVerificationCodeResponseDTO.setEmail(this.validateEmail(email));

        return emailVerificationCodeResponseDTO;
    }
}