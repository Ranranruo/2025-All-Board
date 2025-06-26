package sms.allBoard.App.Auth.DTO;

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
}