package sms.allBoard.Auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sms.allBoard.Auth.DTO.*;
import sms.allBoard.Auth.Exception.SignUpException;
import sms.allBoard.Auth.Exception.TokenRefreshException;
import sms.allBoard.Auth.Exception.VerificationException;
import sms.allBoard.Auth.Service.AuthService;
import sms.allBoard.Common.Enum.ResponseStatus;
import sms.allBoard.Common.Enum.FieldStatus;
import sms.allBoard.Common.Security.Details.MemberDetails;
import sms.allBoard.Common.Service.Auth.Verification.Identifier.EmailIdentifier;
import sms.allBoard.Common.Service.Auth.Verification.Identifier.Identifier;
import sms.allBoard.Common.Service.Auth.Verification.Info.EmailVerificationInfo;
import sms.allBoard.Common.Service.Auth.Verification.Info.VerificationInfo;
import sms.allBoard.Common.Service.Auth.Verification.VerificationService;
import sms.allBoard.Common.Util.ApiResponse;
import sms.allBoard.Common.Util.JwtUtil;
import sms.allBoard.Common.Util.RedisUtil;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final VerificationService verificationService;
    private final AuthValidator authValidator;
    private final JwtUtil jwtUtil;
    private final RedisUtil redisUtil;

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

        // success response
        return ResponseEntity.status(ResponseStatus.CREATED.getCode()).body(new ApiResponse<>(true, ResponseStatus.CREATED, responseBody));
    }
    @PostMapping("/verification/email")
    public ResponseEntity<ApiResponse<VerificationResponseDTO>> sendVerificationEmail(
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

    @PostMapping("/token/access")
    public ResponseEntity<ApiResponse<Object>> accessToken(
            @CookieValue(value = "refresh_token", defaultValue = "") String refreshToken
    ) {
        if(refreshToken == null) {
            throw new TokenRefreshException(ResponseStatus.UNAUTHORIZED);
        }

        String oldRefreshToken = redisUtil.get(refreshToken);
        String username = jwtUtil.getUsername(oldRefreshToken);

        String newAccessToken = jwtUtil.generateAccessToken(username);

        return ResponseEntity
                .status(ResponseStatus.SUCCESS.getCode())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + newAccessToken)
                .body(new ApiResponse<Object>(true, ResponseStatus.SUCCESS, null));
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<ApiResponse<Object>> refreshToken(
            @CookieValue(value = "refresh_token", defaultValue = "") String oldRefreshKey
    ) {
        if (oldRefreshKey == null) {
            throw new TokenRefreshException(ResponseStatus.UNAUTHORIZED);
        }

        String oldRefreshToken = redisUtil.get(oldRefreshKey);
        String username = jwtUtil.getUsername(oldRefreshToken);

        String newRefreshToken = jwtUtil.generateRefreshToken(username);
        String newRefreshKey = UUID.randomUUID().toString();

        redisUtil.delete(oldRefreshKey);
        redisUtil.set(newRefreshKey, newRefreshToken);

        ResponseCookie cookie = ResponseCookie.from("refresh_token", newRefreshKey)
                .httpOnly(true)
                .path("/")
                .maxAge(7 * 24 * 60 * 60)
                .build();

        return ResponseEntity
                .status(ResponseStatus.SUCCESS.getCode())
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new ApiResponse<>(true, ResponseStatus.SUCCESS, null));

    }

    @GetMapping("/test")
    public ResponseEntity<?> test(
            @AuthenticationPrincipal MemberDetails memberDetails
            ) {
        return ResponseEntity.ok(memberDetails);
    }
}

