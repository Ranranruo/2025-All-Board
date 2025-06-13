package sms.allBoard.Auth.Exception;

import lombok.Getter;
import sms.allBoard.Auth.DTO.SignInResponseDTO;
import sms.allBoard.Common.Enum.ResponseStatus;
import sms.allBoard.Common.Interface.ApiException;

@Getter
public class SignInException extends RuntimeException implements ApiException<SignInResponseDTO> {
    private final ResponseStatus responseStatus;
    private final SignInResponseDTO responseBody;
    public SignInException(ResponseStatus responseStatus, SignInResponseDTO responseBody) {
        super();
        this.responseStatus = responseStatus;
        this.responseBody = responseBody;
    }
}