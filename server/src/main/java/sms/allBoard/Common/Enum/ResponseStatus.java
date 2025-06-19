package sms.allBoard.Common.Enum;

import lombok.Getter;

@Getter
public enum ResponseStatus {

    SUCCESS(200),
    CREATED(201),
    BAD_REQUEST(400),
    INVALID(400),
    EMPTY(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    EXISTS(409),
    EXPIRED(419),
    VERIFICATION_FAILED(422);
    private final int code;
    private final String message;

    ResponseStatus(int code) {
        this.code = code;
        this.message = this.name(); // enum 이름 그대로를 메시지로 사용
    }
}