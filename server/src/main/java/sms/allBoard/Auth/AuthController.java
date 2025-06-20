package sms.allBoard.Auth;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sms.allBoard.Auth.DTO.*;
import sms.allBoard.Auth.Exception.SignUpException;
import sms.allBoard.Auth.Exception.VerificationException;
import sms.allBoard.Auth.Service.AuthService;
import sms.allBoard.Common.Enum.ResponseStatus;
import sms.allBoard.Common.Enum.FieldStatus;
import sms.allBoard.Common.Service.Verification.Identifier.EmailIdentifier;
import sms.allBoard.Common.Service.Verification.Identifier.Identifier;
import sms.allBoard.Common.Service.Verification.Info.EmailVerificationInfo;
import sms.allBoard.Common.Service.Verification.Info.VerificationInfo;
import sms.allBoard.Common.Service.Verification.VerificationService;
import sms.allBoard.Common.Util.ApiResponse;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final VerificationService verificationService;
    private final AuthValidator authValidator;

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<SignUpResponseDTO>> signUp(
            @RequestBody(required = false) SignUpRequestDTO requestBody
    ) {
        // init
        SignUpResponseDTO responseBody = new SignUpResponseDTO();
        ResponseStatus responseStatus = ResponseStatus.SUCCESS;

        // null check
        if(requestBody == null) {
            responseBody.setDisplayName(FieldStatus.EMPTY);
            responseBody.setUsername(FieldStatus.EMPTY);
            responseBody.setPassword(FieldStatus.EMPTY);
            responseBody.setEmail(FieldStatus.EMPTY);
            responseBody.setVerificationCode(FieldStatus.EMPTY);
            throw new SignUpException(ResponseStatus.BAD_REQUEST, responseBody);
        }

        // validation
        responseBody = authValidator.validateSignUpRequest(requestBody);

        if(!responseBody.isAllSuccess()) {
            responseStatus = ResponseStatus.INVALID;
        }

        // check exists
        boolean isExists = false;

        if(responseBody.getUsername().equals(FieldStatus.SUCCESS)) {
            boolean isExistsUsername = authService.isExistsUsername(requestBody.getUsername());
            if(isExistsUsername) {
                responseBody.setUsername(FieldStatus.EXISTS);
                isExists = true;
            }
        }
        if(responseBody.getEmail().equals(FieldStatus.SUCCESS)) {
            boolean isExistsEmail = authService.isExistsEmail(requestBody.getEmail());
            if (isExistsEmail) {
                responseBody.setEmail(FieldStatus.EXISTS);
                isExists = true;
            }
        }
        if (isExists && responseStatus == ResponseStatus.SUCCESS) {
            responseStatus = ResponseStatus.EXISTS;
        }

        // verification
        Identifier identifier = new EmailIdentifier(requestBody.getEmail());
        VerificationInfo verificationInfo = new EmailVerificationInfo(identifier, requestBody.getVerificationCode());
        boolean isAuthenticated = verificationService.authenticate(verificationInfo);
        if (!isAuthenticated) {
            responseBody.setVerificationCode(FieldStatus.VERIFICATION_FAILED);
            if(responseStatus == ResponseStatus.SUCCESS) {
                responseStatus = ResponseStatus.VERIFICATION_FAILED;
            }
        }

        if (responseStatus != ResponseStatus.SUCCESS) {
            throw new SignUpException(responseStatus, responseBody);
        }

        // sign up
        authService.signUp(requestBody);

        return ResponseEntity.status(ResponseStatus.CREATED.getCode()).body(new ApiResponse<>(true, ResponseStatus.CREATED, responseBody));
    }
    @PostMapping("/verification/email")
    public ResponseEntity<ApiResponse<VerificationResponseDTO>> mail(
            @RequestBody(required = false) VerificationRequestDTO requestBody
    ) {
        VerificationResponseDTO responseBody = authValidator.validateVerificationRequest(requestBody != null ? requestBody : new VerificationRequestDTO());

        boolean isValidEmail = responseBody.getEmail().equals(FieldStatus.SUCCESS);

        if(!isValidEmail) {
            throw new VerificationException(ResponseStatus.INVALID, responseBody);
        }


        Identifier identifier = new EmailIdentifier(requestBody.getEmail());
         verificationService.send(identifier);

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

