package sms.allBoard.Auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sms.allBoard.Auth.DTO.SignInResponseDTO;
import sms.allBoard.Auth.DTO.SignUpResponseDTO;
import sms.allBoard.Auth.DTO.VerificationResponseDTO;
import sms.allBoard.Auth.Exception.SignInException;
import sms.allBoard.Auth.Exception.SignUpException;
import sms.allBoard.Auth.Exception.TokenRefreshException;
import sms.allBoard.Auth.Exception.VerificationException;
import sms.allBoard.Common.Util.ApiResponse;

@RestControllerAdvice
public class AuthExceptionHandler {

    // 토큰 관련 에러 핸들러
    @ExceptionHandler(TokenRefreshException.class)
    public ResponseEntity<ApiResponse> handleTokenRefreshException(TokenRefreshException e) {
        return ResponseEntity
                .status(e.getResponseStatus().getCode())
                .body(new ApiResponse(false, e.getResponseStatus(), e.getResponseBody()));
    }

    // 회원가입 관련 에러 핸들러
    @ExceptionHandler(SignUpException.class)
    public ResponseEntity<ApiResponse<SignUpResponseDTO>> handleSignUpException(SignUpException e) {
        return ResponseEntity
                .status(e.getResponseStatus().getCode())
                .body(new ApiResponse<SignUpResponseDTO>(false, e.getResponseStatus(), e.getResponseBody()));
    }

    // 로그인 관련 에러 핸들러
    @ExceptionHandler(SignInException.class)
    public ResponseEntity<ApiResponse<SignInResponseDTO>> handleSignInException(SignInException e) {
        return ResponseEntity
                .status(e.getResponseStatus().getCode())
                .body(new ApiResponse<SignInResponseDTO>(false, e.getResponseStatus(), e.getResponseBody()));
    }
    
    // 인증 관련 에러 핸들러
    @ExceptionHandler(VerificationException.class)
    public ResponseEntity<ApiResponse<VerificationResponseDTO>> handleVerificationException(VerificationException e) {
        return ResponseEntity
                .status(e.getResponseStatus().getCode())
                .body(new ApiResponse<VerificationResponseDTO>(false, e.getResponseStatus(), e.getResponseBody()));
    }
}
