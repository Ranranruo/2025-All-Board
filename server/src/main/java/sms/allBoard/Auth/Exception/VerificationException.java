package sms.allBoard.Auth.Exception;

import lombok.Getter;
import sms.allBoard.Auth.DTO.VerificationResponseDTO;
import sms.allBoard.Common.Enum.ResponseStatus;
import sms.allBoard.Common.Interface.ApiException;

@Getter
public class VerificationException extends RuntimeException implements ApiException<VerificationResponseDTO> {
  private final ResponseStatus responseStatus;
  private final VerificationResponseDTO responseBody;
    public VerificationException(ResponseStatus responseStatus, VerificationResponseDTO responseBody) {
      this.responseStatus = ResponseStatus.INVALID;
      this.responseBody = responseBody;
    }
}
