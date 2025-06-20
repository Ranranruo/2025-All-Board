package sms.allBoard.Auth.Exception;

import lombok.Getter;
import sms.allBoard.Auth.DTO.SignInResponseDTO;
import sms.allBoard.Common.Enum.ResponseStatus;
import sms.allBoard.Common.Interface.ApiException;

@Getter
public class TokenRefreshException extends RuntimeException implements ApiException<Object> {
    private final ResponseStatus responseStatus;
    public TokenRefreshException(ResponseStatus responseStatus) {
        super();
        this.responseStatus = responseStatus;
    }
    public Object getResponseBody() {
        return null;
    }
}