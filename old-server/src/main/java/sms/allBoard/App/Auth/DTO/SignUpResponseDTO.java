package sms.allBoard.App.Auth.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private FieldStatus verificationCode;

    @JsonIgnore
    public boolean isAllSuccess() {
        return (
                this.username.equals(FieldStatus.SUCCESS)
                && this.displayName.equals(FieldStatus.SUCCESS)
                && this.password.equals(FieldStatus.SUCCESS)
                && this.email.equals(FieldStatus.SUCCESS)
                && this.verificationCode.equals(FieldStatus.SUCCESS)
        );
    }
}
