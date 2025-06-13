package sms.allBoard.Auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sms.allBoard.Auth.DTO.SignInResponseDTO;
import sms.allBoard.Auth.DTO.SignUpResponseDTO;
import sms.allBoard.Auth.DTO.VerificationResponseDTO;
import sms.allBoard.Auth.Exception.SignInException;
import sms.allBoard.Auth.Exception.SignUpException;
import sms.allBoard.Auth.Exception.VerificationException;
import sms.allBoard.Common.Util.ApiResponse;

@RestControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(SignUpException.class)
    public ResponseEntity<ApiResponse<SignUpResponseDTO>> handleSignUpException(SignUpException e) {
        return ResponseEntity.status(e.getResponseStatus().getCode()).body(new ApiResponse<SignUpResponseDTO>(false, e.getResponseStatus(), e.getResponseBody()));
    }

    @ExceptionHandler(SignInException.class)
    public ResponseEntity<ApiResponse<SignInResponseDTO>> handleSignInException(SignInException e) {
        return ResponseEntity.status(e.getResponseStatus().getCode()).body(new ApiResponse<SignInResponseDTO>(false, e.getResponseStatus(), e.getResponseBody()));
    }
    @ExceptionHandler(VerificationException.class)
    public ResponseEntity<ApiResponse<VerificationResponseDTO>> handleVerificationException(VerificationException e) {
        return ResponseEntity.status(e.getResponseStatus().getCode()).body(new ApiResponse<VerificationResponseDTO>(false, e.getResponseStatus(), e.getResponseBody()));
    }
}
