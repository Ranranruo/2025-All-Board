package sms.allBoard.Common.Interface;

import sms.allBoard.Common.Enum.ResponseStatus;

public interface ApiException<T> {
    ResponseStatus getResponseStatus();
    T getResponseBody();
}
