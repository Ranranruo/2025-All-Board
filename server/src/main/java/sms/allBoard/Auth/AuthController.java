package sms.allBoard.Auth;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sms.allBoard.Auth.DTO.SignUpRequestDTO;
import sms.allBoard.Auth.DTO.SignUpResponseDTO;
import sms.allBoard.Auth.DTO.VerificationRequestDTO;
import sms.allBoard.Auth.DTO.VerificationResponseDTO;
import sms.allBoard.Auth.Exception.SignUpException;
import sms.allBoard.Auth.Exception.VerificationException;
import sms.allBoard.Auth.Service.AuthService;
import sms.allBoard.Auth.Service.VerificationService;
import sms.allBoard.Common.Enum.ResponseStatus;
import sms.allBoard.Common.Enum.FieldStatus;
import sms.allBoard.Common.Util.ApiResponse;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final VerificationService verificationService;
    private final AuthValidator authValidator;
    private final MailService mailService;

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<SignUpResponseDTO>> signUp(
            @RequestBody(required = false) SignUpRequestDTO requestBody
    ) {
        SignUpResponseDTO responseBody = authValidator.validateSignUpRequest(requestBody != null ? requestBody : new SignUpRequestDTO());

        responseBody.setVerificationCode(verificationService.verifyVerificationCode(requestBody.getEmail(), requestBody.getVerificationCode()));

        boolean isValidUsername = responseBody.getUsername().equals(FieldStatus.SUCCESS);
        boolean isValidDisplayName = responseBody.getDisplayName().equals(FieldStatus.SUCCESS);
        boolean isValidPassword = responseBody.getPassword().equals(FieldStatus.SUCCESS);
        boolean isValidEmail = responseBody.getEmail().equals(FieldStatus.SUCCESS);
        boolean isValidVerificationCode = responseBody.getVerificationCode().equals(FieldStatus.SUCCESS);

        if (!(isValidUsername && isValidDisplayName && isValidPassword && isValidEmail && isValidVerificationCode)) {
            throw new SignUpException(ResponseStatus.INVALID, responseBody);
        }

        authService.signUp(requestBody);

        return ResponseEntity.status(ResponseStatus.CREATED.getCode()).body(new ApiResponse<>(true, ResponseStatus.CREATED, responseBody));
    }
    @PostMapping("/verification")
    public ResponseEntity<ApiResponse<VerificationResponseDTO>> mail(
            @RequestBody(required = false) VerificationRequestDTO requestBody
    ) {
        VerificationResponseDTO responseBody = authValidator.validateVerificationRequest(requestBody != null ? requestBody : new VerificationRequestDTO());

        boolean isValidEmail = responseBody.getEmail().equals(FieldStatus.SUCCESS);

        if(!isValidEmail) {
            throw new VerificationException(ResponseStatus.INVALID, responseBody);
        }

        String verificationCode = verificationService.generateVerificationCode();
        verificationService.saveVerificationSession(requestBody.getEmail(), verificationCode);
        mailService.sendVerifyMail(requestBody.getEmail(), verificationCode);

        return ResponseEntity.status(ResponseStatus.SUCCESS.getCode()).body(new ApiResponse<>(true, ResponseStatus.SUCCESS, responseBody));
    }
    @GetMapping("/test")
    public void test(
            HttpSession session
    ) {
        System.out.println(session.getAttribute("verificationEmail"));
        System.out.println(session.getAttribute("verificationCode"));
    }
}

