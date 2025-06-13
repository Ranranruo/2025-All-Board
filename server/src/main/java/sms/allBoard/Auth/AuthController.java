package sms.allBoard.Auth;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import sms.allBoard.Auth.DTO.SignUpRequestDTO;
import sms.allBoard.Auth.DTO.SignUpResponseDTO;
import sms.allBoard.Auth.DTO.VerificationRequestDTO;
import sms.allBoard.Auth.DTO.VerificationResponseDTO;
import sms.allBoard.Auth.Exception.SignUpException;
import sms.allBoard.Common.Enum.ResponseStatus;
import sms.allBoard.Common.Enum.FieldStatus;
import sms.allBoard.Common.Util.ApiResponse;
import sms.allBoard.Common.Util.Mail.MailService;

import java.util.Random;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AuthValidator authValidator;
    private final MailService mailService;

    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<SignUpResponseDTO>> signUp(
            @RequestBody(required = false) SignUpRequestDTO requestBody
    ) {
        SignUpResponseDTO responseBody = authValidator.validateSignUpRequest(requestBody != null ? requestBody : new SignUpRequestDTO());

        boolean isValidUsername = responseBody.getUsername().equals(FieldStatus.SUCCESS);
        boolean isValidDisplayName = responseBody.getDisplayName().equals(FieldStatus.SUCCESS);
        boolean isValidPassword = responseBody.getPassword().equals(FieldStatus.SUCCESS);
        boolean isValidEmail = responseBody.getEmail().equals(FieldStatus.SUCCESS);

        if (!(isValidUsername && isValidDisplayName && isValidPassword && isValidEmail)) {
            throw new SignUpException(ResponseStatus.INVALID, responseBody);
        }

        authService.signUp(requestBody);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<SignUpResponseDTO>(true, ResponseStatus.CREATED, responseBody));
    }
    @PostMapping("/verification")
    public void mail(
            @RequestBody(required = false) VerificationRequestDTO requestBody,
            HttpSession session
    ) {
        VerificationResponseDTO responseBody = authValidator.validateVerificationRequest(requestBody != null ? requestBody : new VerificationRequestDTO());

        boolean isValidEmail = responseBody.getEmail().equals(FieldStatus.SUCCESS);

        if(!isValidEmail) {

        }

        String verificationCode = "";
        for(int i = 0; i < 6; i++) {
            verificationCode += String.valueOf(new Random().nextInt(10));
        }

        session.setAttribute("verificationCode", verificationCode);
        session.setAttribute("verificationCode", verificationCode);


    }
}

