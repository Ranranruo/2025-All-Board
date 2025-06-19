package sms.allBoard.Auth.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import sms.allBoard.Common.Enum.FieldStatus;
import sms.allBoard.Common.Interface.ResponseDTO;

@Getter @Setter
public class SignUpResponseDTO implements ResponseDTO {
    private FieldStatus username;
    private FieldStatus displayName;
    private FieldStatus password;
    private FieldStatus email;

    @Override
    public boolean isAllSuccess() {
        return (
                this.username.equals(FieldStatus.SUCCESS)
                && this.displayName.equals(FieldStatus.SUCCESS)
                && this.password.equals(FieldStatus.SUCCESS)
                && this.email.equals(FieldStatus.SUCCESS)
        );
    }
}
