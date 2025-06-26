package sms.allBoard.App.Auth.DTO;

import lombok.Getter;
import lombok.Setter;
import sms.allBoard.Common.Enum.FieldStatus;
import sms.allBoard.Common.Interface.ResponseDTO;

@Getter @Setter
public class VerificationResponseDTO implements ResponseDTO {
    private FieldStatus email;
}
