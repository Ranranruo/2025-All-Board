package sms.allBoard.Auth.DTO;

import lombok.Getter;
import lombok.Setter;
import sms.allBoard.Common.Enum.FieldStatus;

@Getter @Setter
public class SignUpResponseDTO {
    private FieldStatus username;
    private FieldStatus displayName;
    private FieldStatus password;
    private FieldStatus email;

}
