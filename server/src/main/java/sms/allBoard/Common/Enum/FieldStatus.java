package sms.allBoard.Common.Enum;

import lombok.Getter;

@Getter
public enum FieldStatus {
    SUCCESS,
    ERROR,
    EMPTY,
    EXISTS,
    TOO_SHORT,
    TOO_LONG,
    INVALID,
    NOT_FOUND
}
