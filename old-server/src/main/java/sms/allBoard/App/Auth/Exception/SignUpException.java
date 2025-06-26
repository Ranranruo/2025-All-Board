package sms.allBoard.App.Auth.Exception;

import lombok.Getter;
import sms.allBoard.App.Auth.DTO.SignUpResponseDTO;
import sms.allBoard.Common.Enum.ResponseStatus;
import sms.allBoard.Common.Interface.ApiException;

@Getter
public class SignUpException extends RuntimeException implements ApiException<SignUpResponseDTO> {
    private final ResponseStatus responseStatus;
    private final SignUpResponseDTO responseBody;
    public SignUpException(ResponseStatus responseStatus, SignUpResponseDTO responseBody) {
        this.responseStatus = responseStatus;
        this.responseBody = responseBody;
    }
}