package com.allboard.auth.controller;

import com.allboard.auth.AuthValidator;
import com.allboard.auth.dto.EmailVerificationCodeRequestDTO;
import com.allboard.auth.dto.EmailVerificationCodeResponseDTO;
import com.allboard.auth.dto.SignUpRequestDTO;
import com.allboard.auth.dto.SignUpResponseDTO;
import com.allboard.auth.exception.SignUpException;
import com.allboard.auth.exception.VerificationException;
import com.allboard.auth.feign.verification.VerificationClient;
import com.allboard.auth.feign.verification.dto.EmailVerificationRequestDTO;
import com.allboard.auth.service.AuthService;
import com.github.ranranruo.Response.ApiResponse;
import com.github.ranranruo.Response.FieldStatus;
import com.github.ranranruo.Response.ResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthValidator authValidator;
    private final AuthService authService;
    private final VerificationClient verificationClient;
    private final RabbitTemplate rabbitTemplate;

    @PostMapping("/verification/email")
    public ResponseEntity<ApiResponse<EmailVerificationCodeResponseDTO>> sendVerificationEmail(
            @RequestBody(required = false) EmailVerificationCodeRequestDTO requestBody
    ) {
        EmailVerificationCodeResponseDTO responseBody = new EmailVerificationCodeResponseDTO();

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

        rabbitTemplate.convertAndSend("verification", "email", requestBody);

        return ResponseEntity
                .status(ResponseStatus.SUCCESS.getCode())
                .body(new ApiResponse<EmailVerificationCodeResponseDTO>(true, ResponseStatus.SUCCESS, responseBody));
    }

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
        EmailVerificationRequestDTO emailVerificationDTO = new EmailVerificationRequestDTO();
        emailVerificationDTO.setEmail(requestBody.getEmail());
        emailVerificationDTO.setCode(requestBody.getVerificationCode());

        // 인증 정보로 인증하기
        boolean isAuthenticated = verificationClient.verify(emailVerificationDTO);

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
        return ResponseEntity
                .status(ResponseStatus.CREATED.getCode())
                .body(new ApiResponse<>(true, ResponseStatus.CREATED, responseBody));
    }
}
