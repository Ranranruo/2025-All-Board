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

    // 회원가입
    @PostMapping("/sign-up")
    public ResponseEntity<ApiResponse<SignUpResponseDTO>> signUp(
            @RequestBody(required = false) SignUpRequestDTO requestBody
    ) {
        // init
        // 초기 셋팅

        // 응답 데이터
        SignUpResponseDTO responseBody = new SignUpResponseDTO();

        // 응답 코드
        ResponseStatus responseStatus = ResponseStatus.SUCCESS;

        // null check
        // 요청 데이터가 비어있으면 에러 반환
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

        // SUCCESS 상태가 아닌 필드가 있으면 응답 코드 INVALID로 설정
        if(!responseBody.isAllSuccess()) {
            responseStatus = ResponseStatus.INVALID;
        }

        // check exists
        // 이미 존재하는 필드가 있는지 체크 (하나라도 있으면 true)
        boolean isExists = false;

        // username 필드가 SUCCESS 상태이면 이미 존재하는지 확인
        if(responseBody.getUsername().equals(FieldStatus.SUCCESS)) {
            // 이미 존재하는 username 인지 확인
            boolean isExistsUsername = authService.isExistsUsername(requestBody.getUsername());

            // 이미 존재하면 username 필드를 EXISTS 상태로 설정
            if(isExistsUsername) {
                responseBody.setUsername(FieldStatus.EXISTS);
                isExists = true; // true 변경
            }
        }

        // email 필드가 SUCCESS 상태이면 이미 존재하는지 확인
        if(responseBody.getEmail().equals(FieldStatus.SUCCESS)) {
            // 이미 존재하는 email 인지 확인
            boolean isExistsEmail = authService.isExistsEmail(requestBody.getEmail());

            // 이미 존재하면 email 필드를 EXISTS 상태로 설정
            if (isExistsEmail) {
                responseBody.setEmail(FieldStatus.EXISTS);
                isExists = true; // true 변경
            }
        }
        // 하나라도 존재하는 필드가 있고 현재 응답 코드가 SUCCESS 상태라면 응답 코드 EXISTS 로 설정
        if (isExists && responseStatus == ResponseStatus.SUCCESS) {
            responseStatus = ResponseStatus.EXISTS;
        }

        // verification
        // 이메일 인증

        // 어떤 인증인지에 대한 식별자 생성 (이메일 인증 => EmailIdentifier)
        Identifier identifier = new EmailIdentifier(requestBody.getEmail());
        
        // 식별자를 통해 이메일 인증을 위한 정보 생성 (에메일, 인증코드)
        VerificationInfo verificationInfo = new EmailVerificationInfo(identifier, requestBody.getVerificationCode());

        // 인증 정보로 인증하기
        boolean isAuthenticated = verificationService.authenticate(verificationInfo);
        
        // 인증에 실패했다면 verificationCode 필드 VERIFICATION_FAILED로 설정
        if (!isAuthenticated) {
            responseBody.setVerificationCode(FieldStatus.VERIFICATION_FAILED);
            // 현재 응답 코드가 SUCCESS 라면 VERIFICATION_FAILED로 설정
            if(responseStatus == ResponseStatus.SUCCESS) {
                responseStatus = ResponseStatus.VERIFICATION_FAILED;
            }
        }

        // 현재 응답 코드가 SUCCESS가 아니라면 현재 상태로 에러 던지기
        if (responseStatus != ResponseStatus.SUCCESS) {
            throw new SignUpException(responseStatus, responseBody);
        }

        // sign up
        // 회원가입
        authService.signUp(requestBody);

        // success response
        return ResponseEntity.status(ResponseStatus.CREATED.getCode()).body(new ApiResponse<>(true, ResponseStatus.CREATED, responseBody));
    }
    
    // 이메일 인증 전송
    @PostMapping("/verification/email")
    public ResponseEntity<ApiResponse<VerificationResponseDTO>> sendVerificationEmail(
            @RequestBody(required = false) VerificationRequestDTO requestBody
    ) {
        VerificationResponseDTO responseBody = new VerificationResponseDTO();

        // null check
        if(requestBody == null) {
            responseBody.setEmail(FieldStatus.EMPTY);
            throw new VerificationException(ResponseStatus.BAD_REQUEST, responseBody);
        }

        // validation
        responseBody = authValidator.validateVerificationRequest(requestBody);

        boolean isValidEmail = responseBody.getEmail().equals(FieldStatus.SUCCESS);
        if(!isValidEmail) {
            throw new VerificationException(ResponseStatus.INVALID, responseBody);
        }


        Identifier identifier = new EmailIdentifier(requestBody.getEmail());
         verificationService.send(identifier);

        return ResponseEntity.status(ResponseStatus.SUCCESS.getCode()).body(new ApiResponse<>(true, ResponseStatus.SUCCESS, responseBody));
    }

    // 엑세스 토큰 재발급
    @PostMapping("/token/access")
    public ResponseEntity<ApiResponse<Object>> accessToken(
            @CookieValue(value = "refresh_token", defaultValue = "") String oldRefreshKey
    ) {
        // null check
        // 요청에 리프레시 토큰키가 오지않으면 에러 발생
        if(oldRefreshKey == null) {
            throw new TokenRefreshException(ResponseStatus.UNAUTHORIZED);
        }

        // 이전 리프레시 토큰키로 redis 에서 토큰 불러오기
        String oldRefreshToken = redisUtil.get(oldRefreshKey);

        // null check
        // redis에 요청한 리프레시 토큰이 없으면 에러 발생
        if(oldRefreshToken == null) {
            throw new TokenRefreshException(ResponseStatus.UNAUTHORIZED);
        }

        // 이전 리프레시 토큰에서 username 가져오기
        String username = jwtUtil.getUsername(oldRefreshToken);

        // username 으로 리프레시 토큰 재발급
        String newAccessToken = jwtUtil.generateAccessToken(username);

        // Authorization 헤더에 엑세스 토큰 넣어서 반환
        return ResponseEntity
                .status(ResponseStatus.SUCCESS.getCode())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + newAccessToken)
                .body(new ApiResponse<Object>(true, ResponseStatus.SUCCESS, null));
    }

    // 리프레시 토큰 재발급
    @PostMapping("/token/refresh")
    public ResponseEntity<ApiResponse<Object>> refreshToken(
            @CookieValue(value = "refresh_token", defaultValue = "") String oldRefreshKey
    ) {
        // null check
        if (oldRefreshKey == null) {
            throw new TokenRefreshException(ResponseStatus.UNAUTHORIZED);
        }

        // 이전 리프레시 토큰키로 redis 에서 토큰 불러오기
        String oldRefreshToken = redisUtil.get(oldRefreshKey);
        // 이전 리프레시 토큰에서 username 가져오기
        String username = jwtUtil.getUsername(oldRefreshToken);

        // username 으로 리프레시 토큰 재발급
        String newRefreshToken = jwtUtil.generateRefreshToken(username);

        // 리프레시 토큰 저장할 key 값 생성
        String newRefreshKey = UUID.randomUUID().toString();

        // 이전 리프레시 토큰 redis 에서 삭제
        redisUtil.delete(oldRefreshKey);

        // 재발급한 리프레시 토큰 redis에 저장
        redisUtil.set(newRefreshKey, newRefreshToken);

        // 재발급한 리프레시 토큰 key값 cookie에 저장
        ResponseCookie cookie = ResponseCookie.from("refresh_token", newRefreshKey)
                .httpOnly(true)
                .path("/")
                .maxAge(7 * 24 * 60 * 60) // 우효기간 7일로 설정
                .build();

        // 쿠기 헤더에 넣어서 반환
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

