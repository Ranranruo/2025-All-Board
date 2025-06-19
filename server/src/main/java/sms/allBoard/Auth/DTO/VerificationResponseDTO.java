package sms.allBoard.Auth.DTO;

import lombok.Getter;
import lombok.Setter;
import sms.allBoard.Common.Enum.FieldStatus;
import sms.allBoard.Common.Interface.ResponseDTO;

@Getter @Setter
public class VerificationResponseDTO implements ResponseDTO {
    private FieldStatus email;
    @Override
    public boolean isAllSuccess() {
        return this.email.equals(FieldStatus.SUCCESS);
    }
}
