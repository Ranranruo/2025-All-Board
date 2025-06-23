package sms.allBoard.Common.Util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sms.allBoard.Common.Enum.ResponseStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApiResponse<T> {
    private boolean success;
    private ResponseStatus responseStatus;
    private T data;
}
