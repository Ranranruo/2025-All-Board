package com.allboard.auth.exception;

import com.allboard.auth.dto.SignInResponseDTO;
import com.github.ranranruo.Response.ResponseStatus;
import lombok.Getter;

@Getter
public class SignInException extends RuntimeException {
    private final ResponseStatus responseStatus;
    private final SignInResponseDTO responseBody;
    public SignInException(ResponseStatus responseStatus, SignInResponseDTO responseBody) {
        super();
        this.responseStatus = responseStatus;
        this.responseBody = responseBody;
    }
}