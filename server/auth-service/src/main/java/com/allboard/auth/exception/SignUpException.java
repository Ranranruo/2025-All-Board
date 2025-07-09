package com.allboard.auth.exception;

import com.allboard.auth.dto.SignUpResponseDTO;
import com.github.ranranruo.Response.ResponseStatus;
import lombok.Getter;

@Getter
public class SignUpException extends RuntimeException {
    private final ResponseStatus responseStatus;
    private final SignUpResponseDTO responseBody;
    public SignUpException(ResponseStatus responseStatus, SignUpResponseDTO responseBody) {
        this.responseStatus = responseStatus;
        this.responseBody = responseBody;
    }
}