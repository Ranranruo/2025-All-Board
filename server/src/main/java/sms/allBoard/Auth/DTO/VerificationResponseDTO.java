package sms.allBoard.Auth.DTO;

import lombok.Getter;
import lombok.Setter;
import sms.allBoard.Common.Enum.FieldStatus;

@Getter @Setter
public class VerificationResponseDTO {
    private FieldStatus email;
}
