package com.allboard.auth.exception;

import com.github.ranranruo.Response.ResponseStatus;
import lombok.Getter;

@Getter
public class TokenRefreshException extends RuntimeException {
    private final ResponseStatus responseStatus;
    public TokenRefreshException(ResponseStatus responseStatus) {
        super();
        this.responseStatus = responseStatus;
    }
    public Object getResponseBody() {
        return null;
    }
}