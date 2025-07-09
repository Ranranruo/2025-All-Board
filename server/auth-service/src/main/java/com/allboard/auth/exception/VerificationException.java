package com.allboard.auth.exception;

import com.allboard.auth.dto.EmailVerificationCodeResponseDTO;
import com.github.ranranruo.Response.ResponseStatus;
import lombok.Getter;

@Getter
public class VerificationException extends RuntimeException {
  private final ResponseStatus responseStatus;
  private final EmailVerificationCodeResponseDTO responseBody;
    public VerificationException(ResponseStatus responseStatus, EmailVerificationCodeResponseDTO responseBody) {
      this.responseStatus = ResponseStatus.INVALID;
      this.responseBody = responseBody;
    }
}