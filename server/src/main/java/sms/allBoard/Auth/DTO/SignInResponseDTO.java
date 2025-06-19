package sms.allBoard.Auth.DTO;

import lombok.*;
import sms.allBoard.Common.Enum.FieldStatus;
import sms.allBoard.Common.Interface.ResponseDTO;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignInResponseDTO implements ResponseDTO {
    private FieldStatus username;
    private FieldStatus password;
    @Override
    public boolean isAllSuccess() {
        return (
                this.username.equals(FieldStatus.SUCCESS)
                && this.password.equals(FieldStatus.SUCCESS)
        );
    }
}